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

import java.util.List;
import java.util.Optional;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.OutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.SerializingField;
import weliyek.amat.base.output.SerializingFieldCore;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingSubfieldHandler;
import weliyek.amat.basic.sequence.SequenceWritingRuntime;

public class FixedSizeByteArraySerializing
    implements ByteArrayWriting<
                        OperationSettings,
                        SequenceWritingRuntime<OutputBytestream>,
                        SerializingResult,
                        FixedSizeByteArray>,
               FixedSizePrimitiveArraySerializerWriting<
                        ByteArrayWrapper,
                        OperationSettings,
                        SequenceWritingRuntime<OutputBytestream>,
                        SerializingResult,
                        FixedSizeByteArray>
{

  final SimplifiedPrimitiveArraySerializingCore<
                        ByteArrayWrapper,
                        OperationSettings,
                        FixedSizeByteArray,
                        FixedSizeByteArraySerializing> operationCore;

  FixedSizeByteArraySerializing(
    int index,
    ByteArrayWrapper serializable,
    OperationSettings settings,
    OutputBytestreamGeneralBase<?> parentBytestream,
    SerializingFieldCore<
      ByteArrayWrapper,?,FixedSizeByteArray,?,?,?> serializingfieldCore,
    SimplifiedPrimitiveArraySerializerCore<
      ByteArrayWrapper,?,?,OperationSettings,FixedSizeByteArraySerializing,FixedSizeByteArray> definitionCore) {
    this.operationCore = new SimplifiedPrimitiveArraySerializingCore<
                                  ByteArrayWrapper,
                                  OperationSettings,
                                  FixedSizeByteArray,
                                  FixedSizeByteArraySerializing>(
                                      index,
                                      serializable,
                                      settings,
                                      parentBytestream,
                                      serializingfieldCore,
                                      definitionCore,
                                      this,
                                      PrimitiveArrayUtils::onFixedSizeSerilizingInitialization);
  }

  @Override
  public ByteArrayWrapper serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public List<SerializingSubfieldHandler<?,?,?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public FixedSizeByteArray definition() {
    return this.operationCore.definition();
  }

  @Override
  public OperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public SequenceWritingRuntime<OutputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<SerializingResult> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public SerializingField<ByteArrayWrapper, FixedSizeByteArray, ?>
      packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public int getRequestedLength() {
    return this.operationCore.getRequestedLength();
  }

}
