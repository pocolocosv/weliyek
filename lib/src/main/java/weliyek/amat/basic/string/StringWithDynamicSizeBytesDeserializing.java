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
package weliyek.amat.basic.string;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import weliyek.amat.base.WkSzOperationSettings;
import weliyek.amat.base.input.WkSzPacketReaderField;
import weliyek.amat.base.input.WkSzPacketReaderFieldCore;
import weliyek.amat.base.input.WkSzReadingResult;
import weliyek.amat.base.input.WkSzReadingRuntime;
import weliyek.amat.base.input.WkSzInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.base.input.WkSzPacketReaderSubfield;
import weliyek.amat.basic.number.WkSzNumberDefinition;
import weliyek.amat.basic.number.WkSzNumberReader;
import weliyek.ketza.util.array.ByteArrayWrapper;
import weliyek.ketza.util.array.DynamicByteArray;
import weliyek.ketza.util.array.DynamicByteArrayDeserializing;

public class StringWithDynamicSizeBytesDeserializing<
                        ZT extends Number,
                        ZXD extends WkSzNumberDefinition<ZT,ZXO>,
                        ZXO extends WkSzNumberReader<ZT,WkSzOperationSettings,?,?,ZXD>>
    implements WkSzStringFromBytesReader<
                        WkSzOperationSettings,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<String>,
                        StringWithDynamicSizeBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        DynamicByteArrayDeserializing<ZT,ZXO,ZXD>>
{

  final SimplifiedStringFromBytesReadingCore<
                        WkSzOperationSettings,
                        StringWithDynamicSizeBytesDeserializing<ZT,ZXD,ZXO>,
                        StringWithDynamicSizeBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        WkSzOperationSettings,
                        DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,
                        DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>>
                            operationCore;

  StringWithDynamicSizeBytesDeserializing(
    int index,
    WkSzOperationSettings settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<String,?,StringWithDynamicSizeBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,?,?,?>
      deserializingfieldCore,
    SimplifiedStringFromBytesCore<
      WkSzOperationSettings,StringWithDynamicSizeBytesDeserializing<ZT,ZXD,ZXO>,
      StringWithDynamicSizeBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,?,?,?,
      WkSzOperationSettings,DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,
      DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,?,?,?,?,?> definitionCore) {
    this.operationCore = new SimplifiedStringFromBytesReadingCore<
        WkSzOperationSettings,
        StringWithDynamicSizeBytesDeserializing<ZT,ZXD,ZXO>,
        StringWithDynamicSizeBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,
        WkSzOperationSettings,
        DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,
        DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>>(
                                      index,
                                      settings,
                                      parentBytestream,
                                      deserializingfieldCore,
                                      definitionCore,
                                      this);
  }

  @Override
  public
  WkSzPacketReaderSubfield<
                        ByteArrayWrapper,
                        DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        DynamicByteArrayDeserializing<ZT, ZXO, ZXD>>
  primitiveArray() {
    return this.operationCore.primitiveArray();
  }

  @Override
  public StringWithDynamicSizeBytes<ZT,ZXD,ZXO,?,?,? extends ZXD> definition() {
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
  public
  WkSzPacketReaderField<String, StringWithDynamicSizeBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,?>
  packetField() {
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
  WkSzPacketReaderSubfield<ByteArrayWrapper, DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>, DynamicByteArrayDeserializing<ZT, ZXO, ZXD>>
  bytes() {
    return this.operationCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.operationCore.charset();
  }

}
