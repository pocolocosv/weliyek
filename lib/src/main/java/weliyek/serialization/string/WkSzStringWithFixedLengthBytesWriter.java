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
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketWriterField;
import weliyek.serialization.WkSzPacketWriterFieldCore;
import weliyek.serialization.WkSzPacketWriterSubfield;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSzFixedSizeByteArray;
import weliyek.util.array.WkSzFixedSizeByteArrayWriter;

public class WkSzStringWithFixedLengthBytesWriter
    implements WkSzStringFromBytesWriter<
                        WkSzOperationSettings,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        WkSzStringWithFixedLengthBytes,
                        WkSzFixedSizeByteArray,
                        WkSzFixedSizeByteArrayWriter>
{

  final SimpleStringFromBytesWritingCore<
                        WkSzOperationSettings,
                        WkSzStringWithFixedLengthBytesWriter,
                        WkSzStringWithFixedLengthBytes,
                        WkSzOperationSettings,
                        WkSzFixedSizeByteArrayWriter,
                        WkSzFixedSizeByteArray> operationCore;

  WkSzStringWithFixedLengthBytesWriter(
    int index,
    String serializable,
    WkSzOperationSettings settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<
      String,?,WkSzStringWithFixedLengthBytes,?,?,?> serializingfieldCore,
    SimplifiedStringFromBytesCore<
      ?,?,?,WkSzOperationSettings,WkSzStringWithFixedLengthBytesWriter,
      WkSzStringWithFixedLengthBytes,?,?,?,WkSzOperationSettings,
      WkSzFixedSizeByteArrayWriter,WkSzFixedSizeByteArray,?,
      ? extends WkSzStringWithFixedLengthBytes> definitionCore) {
    this.operationCore = new SimpleStringFromBytesWritingCore<WkSzOperationSettings,
        WkSzStringWithFixedLengthBytesWriter,
        WkSzStringWithFixedLengthBytes,
        WkSzOperationSettings,
        WkSzFixedSizeByteArrayWriter,
        WkSzFixedSizeByteArray>(
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
  WkSzPacketWriterSubfield<WkByteArray, WkSzFixedSizeByteArray, WkSzFixedSizeByteArrayWriter>
  primitiveArray() {
    return this.operationCore.primitiveArray();
  }

  @Override
  public WkSzStringWithFixedLengthBytes definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSzOperationSettings settings() {
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
  public WkSzPacketWriterField<String, WkSzStringWithFixedLengthBytes, ?> packetField() {
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
  WkSzPacketWriterSubfield<WkByteArray, WkSzFixedSizeByteArray, WkSzFixedSizeByteArrayWriter>
  bytes() {
    return this.operationCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.operationCore.charset();
  }

}
