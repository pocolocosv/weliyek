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
package weliyek.serialization.string;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOptionalLengthOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketWriterField;
import weliyek.serialization.WkSzPacketWriterFieldCore;
import weliyek.serialization.WkSzPacketWriterSubfield;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSzVariableSizeByteArray;
import weliyek.util.array.WkSzVariableSizeByteArrayWriter;

public class StringWithVariableLengthBytesSerializing
    implements WkSzStringFromBytesWriter<
                        WkSzOptionalLengthOperationSettings,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        StringWithVariableLengthBytes,
                        WkSzVariableSizeByteArray,
                        WkSzVariableSizeByteArrayWriter>
{

  final SimpleStringFromBytesWritingCore<
                        WkSzOptionalLengthOperationSettings,
                        StringWithVariableLengthBytesSerializing,
                        StringWithVariableLengthBytes,
                        WkSzOperationSettings,
                        WkSzVariableSizeByteArrayWriter,
                        WkSzVariableSizeByteArray> operationCore;

  StringWithVariableLengthBytesSerializing(
    int index,
    String serializable,
    WkSzOptionalLengthOperationSettings settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<
      String,?,StringWithVariableLengthBytes,?,?,?> serializingfieldCore,
    SimplifiedStringFromBytesCore<
      ?,?,?,WkSzOptionalLengthOperationSettings,StringWithVariableLengthBytesSerializing,
      StringWithVariableLengthBytes,?,?,?,WkSzOperationSettings,
      WkSzVariableSizeByteArrayWriter,WkSzVariableSizeByteArray,?,
      ? extends StringWithVariableLengthBytes> definitionCore) {
    this.operationCore = new SimpleStringFromBytesWritingCore<
                                WkSzOptionalLengthOperationSettings,
                                StringWithVariableLengthBytesSerializing,
                                StringWithVariableLengthBytes,
                                WkSzOperationSettings,
                                WkSzVariableSizeByteArrayWriter,
                                WkSzVariableSizeByteArray>(
                                    index,
                                    serializable,
                                    settings,
                                    parentBytestream,
                                    serializingfieldCore,
                                    definitionCore,
                                    this);
  }

  @Override
  public
  WkSzPacketWriterSubfield<WkByteArray, WkSzVariableSizeByteArray, WkSzVariableSizeByteArrayWriter>
  primitiveArray() {
    return this.operationCore.primitiveArray();
  }

  @Override
  public StringWithVariableLengthBytes definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSzOptionalLengthOperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSzWritingRuntime<WkSzOutputBytestream> dashboard() {
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
  public WkSzPacketWriterField<String, StringWithVariableLengthBytes, ?> packetField() {
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
  public String serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public
  WkSzPacketWriterSubfield<WkByteArray, WkSzVariableSizeByteArray, WkSzVariableSizeByteArrayWriter>
  bytes() {
    return this.operationCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.operationCore.charset();
  }

}
