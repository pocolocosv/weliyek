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

import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketReaderField;
import weliyek.serialization.WkSzPacketReaderFieldCore;
import weliyek.serialization.WkSzPacketReaderSubfield;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSzFixedSizeByteArray;
import weliyek.util.array.WkSzFixedSizeByteArrayReader;

public class WkSzStringWithFixedLengthBytesReader
    implements WkSzStringFromBytesReader<
                        WkSzOperationSettings,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<String>,
                        WkSzStringWithFixedLengthBytes,
                        WkSzFixedSizeByteArray,
                        WkSzFixedSizeByteArrayReader>
{

  final SimplifiedStringFromBytesReadingCore<
                        WkSzOperationSettings,
                        WkSzStringWithFixedLengthBytesReader,
                        WkSzStringWithFixedLengthBytes,
                        WkSzOperationSettings,
                        WkSzFixedSizeByteArrayReader,
                        WkSzFixedSizeByteArray> operationCore;

  WkSzStringWithFixedLengthBytesReader(
    int index,
    WkSzOperationSettings settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<
      String,?,WkSzStringWithFixedLengthBytes,?,?,?> deserializingfieldCore,
    SimplifiedStringFromBytesCore<
      WkSzOperationSettings,WkSzStringWithFixedLengthBytesReader,
      WkSzStringWithFixedLengthBytes,?,?,?,WkSzOperationSettings,
      WkSzFixedSizeByteArrayReader,WkSzFixedSizeByteArray,?,?,?,?,
      ? extends WkSzStringWithFixedLengthBytes> definitionCore) {
    this.operationCore = new SimplifiedStringFromBytesReadingCore<
                                WkSzOperationSettings,
                                WkSzStringWithFixedLengthBytesReader,
                                WkSzStringWithFixedLengthBytes,
                                WkSzOperationSettings,
                                WkSzFixedSizeByteArrayReader,
                                WkSzFixedSizeByteArray>(
                                  index,
                                  settings,
                                  parentBytestream,
                                  deserializingfieldCore,
                                  definitionCore,
                                  this);
  }

  @Override
  public
  WkSzPacketReaderSubfield<WkByteArray, WkSzFixedSizeByteArray, WkSzFixedSizeByteArrayReader>
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
  public WkSzReadingRuntime<WkSzInputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSzReadingResult<String>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSzPacketReaderField<String, WkSzStringWithFixedLengthBytes, ?> packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public List<WkSzPacketReaderSubfield<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public
  WkSzPacketReaderSubfield<WkByteArray, WkSzFixedSizeByteArray, WkSzFixedSizeByteArrayReader>
  bytes() {
    return this.operationCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.operationCore.charset();
  }

}
