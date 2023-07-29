/*
 * Weliyek Java Library
 * Copyright (C) 2023  Ricardo Villalobos - All Rights Reserved
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package weliyek.amat.serialization.primitive;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.amat.base.OperationException;
import weliyek.amat.basic.sequence.SequenceReadingRuntimeControl;
import weliyek.amat.basic.serializer.InputSerializationEngine;
import weliyek.amat.basic.serializer.PrimitiveArraySerializerReading;
import weliyek.ketza.util.array.PrimitiveArrayWrapper;

public abstract class SequenceInputSerializationRule<
                        X extends PrimitiveArrayWrapper<V, ?>,
                        V,
                        QC extends SequenceReadingRuntimeControl<?,?,?>>
        extends InputSerializationEngine<
                        X,
                        QC,
                        PrimitiveArraySerializerReading<X,?,?,?,?>>
{

  private static final Logger logger = LoggerFactory.getLogger(SequenceInputSerializationRule.class);

  private final Function<V, X> sequenceToDeserialized;
  private final int primitiveByteLength;
  private final int stepSize;
  private V array;
  private int start = -1;
  private int stop = 0;

  protected SequenceInputSerializationRule(
      Function<V, X> sequenceToDeserialized,
      int elementByteLength,
      int stepSize,
      QC runtimeCtrl,
      PrimitiveArraySerializerReading<X,?,?,?,?> readingOperation) {
    super(runtimeCtrl, readingOperation);
    this.sequenceToDeserialized = Objects.requireNonNull(sequenceToDeserialized);
    this.primitiveByteLength = elementByteLength;
    this.stepSize = stepSize;
    if (0 != (this.stepSize % elementByteLength)) {
      throw new IllegalArgumentException("Buffer size for deserialinzing array must be a multiple of the primitive size");
    }
  }

  @Override
  protected final void onStart() {
    this.array = newEmptyArray();
    onSequenceDeserializationStart();
    logger.debug(operation().name()
        + " reading primitive ("
        + primitiveByteLength()
        + " bytes) array with lenght "
        + sequenceExpectedLength()
        + " and step size "
        + getSerializationStepSize());
  }

  private void setNextCycleStartStop() {
    this.start = this.stop;
    int elemNum = getSerializationStepSize() / primitiveByteLength();
    this.stop = Math.min(this.stop + elemNum, sequenceExpectedLength());
    getRuntimeControl().setNextElementIndex(this.stop);
  }

  protected abstract V newEmptyArray();

  protected abstract void onSequenceDeserializationStart();

  @Override
  protected final void readBytes() {
    setNextCycleStartStop();
    int i = -1;
    try {
      logger.debug(operation().name() + " reading primitives [" + this.start + ".." + (this.stop - 1) + "]");
      for(i = this.start; i < this.stop; i++) {
            readSequenceElement(i);
      }
    } catch (IOException e) {
      throw new OperationException(operation(), "Failed to read element with index " + i);
    }
  }

  protected abstract void readSequenceElement(int i) throws IOException;

  @Override
  public final X getDeserialized() {
    return this.sequenceToDeserialized.apply(getArray());
  }

  protected final V getArray() {
    return this.array;
  }

  @Override
  protected long requestedBytesToIO() {
    return sequenceExpectedLength() * primitiveByteLength();
  }

  @Override
  public final int primitiveByteLength() {
    return this.primitiveByteLength;
  }

  protected final int sequenceExpectedLength() {
    return operation().getRequestedLength();
  }

  private final int getSerializationStepSize() {
    return operation().packetField().protocolField().definition().getSerializationStepSize();
  }

}
