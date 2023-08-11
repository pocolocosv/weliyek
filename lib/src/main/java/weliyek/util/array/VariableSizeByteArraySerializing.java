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
package weliyek.util.array;

import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketWriterField;
import weliyek.serialization.WkSzPacketWriterFieldCore;
import weliyek.serialization.WkSzPacketWriterSubfield;
import weliyek.serialization.WkSzSequenceWritingRuntime;
import weliyek.serialization.WkSzWritingResult;

public class VariableSizeByteArraySerializing
    implements ByteArrayWriting<
                        WkSzOperationSettings,
                        WkSzSequenceWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        VariableSizeByteArray>,
               VariableSizePrimitiveArraySerializerWriting<
                        ByteArrayWrapper,
                        WkSzOperationSettings,
                        WkSzSequenceWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        VariableSizeByteArray>
{

  final SimplifiedPrimitiveArraySerializingCore<
                    ByteArrayWrapper,
                    WkSzOperationSettings,
                    VariableSizeByteArray,
                    VariableSizeByteArraySerializing> operationCore;

  VariableSizeByteArraySerializing(
    int index,
    ByteArrayWrapper serializable,
    WkSzOperationSettings settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<
      ByteArrayWrapper,?,VariableSizeByteArray,?,?,?> serializingfieldCore,
    SimplifiedPrimitiveArraySerializerCore<
      ByteArrayWrapper,?,?,WkSzOperationSettings,VariableSizeByteArraySerializing,VariableSizeByteArray> definitionCore) {
    this.operationCore = new SimplifiedPrimitiveArraySerializingCore<
                                ByteArrayWrapper,
                                WkSzOperationSettings,
                                VariableSizeByteArray,
                                VariableSizeByteArraySerializing>(
                                    index,
                                    serializable,
                                    settings,
                                    parentBytestream,
                                    serializingfieldCore,
                                    definitionCore,
                                    this,
                                    PrimitiveArrayUtils::onVariableSizeSerializingInitialization);
  }

  @Override
  public VariableSizeByteArray definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSzOperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSzSequenceWritingRuntime<WkSzOutputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSzWritingResult> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSzPacketWriterField<ByteArrayWrapper, VariableSizeByteArray, ?>
  packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public List<WkSzPacketWriterSubfield<?,?,?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public ByteArrayWrapper serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public int getRequestedLength() {
    return this.operationCore.getRequestedLength();
  }

}
