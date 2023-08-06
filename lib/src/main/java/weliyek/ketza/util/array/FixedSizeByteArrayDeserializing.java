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
import weliyek.amat.base.input.WkSzPacketReaderField;
import weliyek.amat.base.input.WkSzPacketReaderFieldCore;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.WkSzPacketReaderSubfield;
import weliyek.amat.base.input.SequenceReadingRuntime;
import weliyek.amat.base.input.WkSzInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;

public class FixedSizeByteArrayDeserializing
    implements ByteArrayReading<
                        OperationSettings,
                        SequenceReadingRuntime<WkSzInputBytestream>,
                        DeserializingResult<ByteArrayWrapper>,
                        FixedSizeByteArray>,
               WkSzSerializerReaderFixedSizePrimitiveArray<
                        ByteArrayWrapper,
                        OperationSettings,
                        SequenceReadingRuntime<WkSzInputBytestream>,
                        DeserializingResult<ByteArrayWrapper>,
                        FixedSizeByteArray>
{

  final SimplifiedPrimitiveArrayDeserializingCore<
                        ByteArrayWrapper,
                        OperationSettings,
                        FixedSizeByteArray,
                        FixedSizeByteArrayDeserializing> operationCore;

  FixedSizeByteArrayDeserializing(
    int index,
    OperationSettings settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<
      ByteArrayWrapper,?,FixedSizeByteArray,?,?,?> deserializingfieldCore,
    SimplifiedPrimitiveArraySerializerCore<
      ByteArrayWrapper,OperationSettings,FixedSizeByteArrayDeserializing,?,?,FixedSizeByteArray> definitionCore) {
    this.operationCore = new SimplifiedPrimitiveArrayDeserializingCore<
                                ByteArrayWrapper,
                                OperationSettings,
                                FixedSizeByteArray,
                                FixedSizeByteArrayDeserializing>(
                                    index,
                                    settings,
                                    parentBytestream,
                                    deserializingfieldCore,
                                    definitionCore,
                                    this,
                                    PrimitiveArrayUtils::onFixedSizeDeserilizingInitialization);
  }

  @Override
  public List<WkSzPacketReaderSubfield<?,?,?>> subfields() {
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
  public SequenceReadingRuntime<WkSzInputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<DeserializingResult<ByteArrayWrapper>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSzPacketReaderField<ByteArrayWrapper, FixedSizeByteArray, ?>
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
