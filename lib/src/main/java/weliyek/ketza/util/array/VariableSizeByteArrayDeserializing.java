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

import weliyek.amat.base.input.WkSzPacketReaderField;
import weliyek.amat.base.input.WkSzPacketReaderFieldCore;
import weliyek.amat.base.input.WkSzReadingResult;
import weliyek.amat.base.input.WkSzPacketReaderSubfield;
import weliyek.amat.base.input.WkSzSequenceReadingRuntime;
import weliyek.amat.base.input.WkSzInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.basic.dynamic.sequence.WkSzVariableLengthOperationSettings;

public final class VariableSizeByteArrayDeserializing
    implements ByteArrayReading<
                        WkSzVariableLengthOperationSettings,
                        WkSzSequenceReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<ByteArrayWrapper>,
                        VariableSizeByteArray>,
               VariableSizePrimitiveArraySerializerReading<
                        ByteArrayWrapper,
                        WkSzVariableLengthOperationSettings,
                        WkSzSequenceReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<ByteArrayWrapper>,
                        VariableSizeByteArray>
{

  final SimplifiedPrimitiveArrayDeserializingCore<
                    ByteArrayWrapper,
                    WkSzVariableLengthOperationSettings,
                    VariableSizeByteArray,
                    VariableSizeByteArrayDeserializing> operationCore;

  VariableSizeByteArrayDeserializing(
    int index,
    WkSzVariableLengthOperationSettings settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<
      ByteArrayWrapper,?,VariableSizeByteArray,?,?,?> deserializingfieldCore,
    SimplifiedPrimitiveArraySerializerCore<
      ByteArrayWrapper,WkSzVariableLengthOperationSettings,VariableSizeByteArrayDeserializing,?,?,VariableSizeByteArray> definitionCore) {
    this.operationCore = new SimplifiedPrimitiveArrayDeserializingCore<
        ByteArrayWrapper,
        WkSzVariableLengthOperationSettings,
        VariableSizeByteArray,
        VariableSizeByteArrayDeserializing>(
                                    index,
                                    settings,
                                    parentBytestream,
                                    deserializingfieldCore,
                                    definitionCore,
                                    this,
                                    PrimitiveArrayUtils::onVariableSizeDeserilizingInitialization);
  }

  @Override
  public List<WkSzPacketReaderSubfield<?,?,?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public VariableSizeByteArray definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSzVariableLengthOperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSzSequenceReadingRuntime<WkSzInputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSzReadingResult<ByteArrayWrapper>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSzPacketReaderField<ByteArrayWrapper, VariableSizeByteArray, ?>
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
