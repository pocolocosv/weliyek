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
package weliyek.ketza.util.array;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.amat.basic.sequence.SequenceWritingRuntimeControl;
import weliyek.amat.basic.serializer.OutputSerializationEngine;
import weliyek.amat.basic.serializer.WkSzPrimitiveArraySerializerWriter;
import weliyek.serialization.WkSzOperationException;

public abstract class PrimitiveArrayOutputSerializationEngine<
                        Y extends PrimitiveArrayWrapperBase<V, ?>,
                        V,
                        QC extends SequenceWritingRuntimeControl<?,?,?>>
        extends OutputSerializationEngine<
                        Y,
                        QC,
                        WkSzPrimitiveArraySerializerWriter<? extends Y,?,?,?,?>>
{

  private static final Logger logger = LoggerFactory.getLogger(PrimitiveArrayOutputSerializationEngine.class);

  private final int primitiveByteLength;
  private int start = -1;
  private int stop = 0;

    protected PrimitiveArrayOutputSerializationEngine(
      QC runtimeControl,
      WkSzPrimitiveArraySerializerWriter<? extends Y,?,?,?,?>  writingOperation,
      int primitiveByteLength) {
      super(runtimeControl, writingOperation);
      this.primitiveByteLength = primitiveByteLength;
    }

    @Override
    protected void onStart() {
      logger.debug(operation().name()
                   + " writing primitive ("
                   + primitiveByteLength()
                   + " byte(s) long) array with lenght "
                   + getArrayLength()
                   + " and step size "
                   + getSerializationStepSize());
      // Nothing to do.
    }

    @Override
    protected void onDone() {
      // Do nothing
    }

    private void setNextCycleStartStop() {
      this.start = this.stop;
      int elemNum = getSerializationStepSize() / primitiveByteLength();
      this.stop = Math.min(this.stop + elemNum, getArrayLength());
      getRuntimeControl().setNextElementIndex(this.stop);
    }

    @Override
    protected final void onBytesLeftToProcess() {
      try {
        setNextCycleStartStop();
        int absStart = getSerializable().absolutePos(this.start);
        int absLast = getSerializable().absolutePos(this.stop - 1);
        logger.debug(operation().name() + " writing primitives [" + this.start + ".." + (this.stop - 1)
                     + "] (abs: [" + absStart + ".." + absLast + "])");
        for(int i = absStart; i <= absLast; i++) {
            writeElementAtAbsolutePos(i);
        }
      } catch (IOException e) {
        throw new WkSzOperationException(operation());
      }
    }

    protected abstract int writeElementAtAbsolutePos(int index) throws IOException;

    @Override
    public final int primitiveByteLength() {
      return this.primitiveByteLength;
    }

    private final int getSerializationStepSize() {
      return operation().packetField().structComponent().definition().getSerializationStepSize();
    }

    protected final int getArrayLength() {
      return getSerializable().getLength();
    }

    protected V getArray() {
      return getSerializable().getArray();
    }

    @Override
    protected long requestedBytesToIO() {
      return getArrayLength() * primitiveByteLength();
    }

}
