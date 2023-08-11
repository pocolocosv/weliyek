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
import weliyek.serialization.number.WkSzNumberDefinition;
import weliyek.serialization.number.WkSzNumberWriter;
import weliyek.serialization.util.array.ByteArrayWrapper;
import weliyek.serialization.util.array.DynamicByteArray;
import weliyek.serialization.util.array.DynamicByteArraySerialzing;

public class StringWithDynamicSizeBytesSerializing<
                        ZT extends Number,
                        ZYD extends WkSzNumberDefinition<ZT,?>,
                        ZYO extends WkSzNumberWriter<ZT,WkSzOperationSettings,?,?,ZYD>>
    implements WkSzStringFromBytesWriter<
                        WkSzOperationSettings,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        StringWithDynamicSizeBytes<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        DynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        DynamicByteArraySerialzing<ZT,ZYO,ZYD>>
{

  final SimpleStringFromBytesWritingCore<
              WkSzOperationSettings,
              StringWithDynamicSizeBytesSerializing<ZT,ZYD,ZYO>,
              StringWithDynamicSizeBytes<ZT,?,?,ZYD,ZYO,? extends ZYD>,
              WkSzOperationSettings,
              DynamicByteArraySerialzing<ZT,ZYO,ZYD>,
              DynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>> operationCore;

  StringWithDynamicSizeBytesSerializing(
    int index,
    String serializable,
    WkSzOperationSettings settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<
      String,?,StringWithDynamicSizeBytes<ZT,?,?,ZYD,ZYO,? extends ZYD>,?,?,?> serializingfieldCore,
    SimplifiedStringFromBytesCore<
      ?,?,?,WkSzOperationSettings,StringWithDynamicSizeBytesSerializing<ZT,ZYD,ZYO>,
      StringWithDynamicSizeBytes<ZT,?,?,ZYD,ZYO,? extends ZYD>,?,?,?,
      WkSzOperationSettings,DynamicByteArraySerialzing<ZT,ZYO,ZYD>,
      DynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,?,?> definitionCore) {
    this.operationCore = new SimpleStringFromBytesWritingCore<
                                  WkSzOperationSettings,
                                  StringWithDynamicSizeBytesSerializing<ZT,ZYD,ZYO>,
                                  StringWithDynamicSizeBytes<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                                  WkSzOperationSettings,
                                  DynamicByteArraySerialzing<ZT,ZYO,ZYD>,
                                  DynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>>(
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
  WkSzPacketWriterSubfield<ByteArrayWrapper, DynamicByteArray<ZT,?,?,ZYD,ZYO, ? extends ZYD>, DynamicByteArraySerialzing<ZT, ZYO, ZYD>>
  primitiveArray() {
    return this.operationCore.primitiveArray();
  }

  @Override
  public StringWithDynamicSizeBytes<ZT,?,?,ZYD,ZYO,? extends ZYD> definition() {
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
  public
  WkSzPacketWriterField<String, StringWithDynamicSizeBytes<ZT,?,?,ZYD,ZYO,? extends ZYD>,?>
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
  public String serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public
  WkSzPacketWriterSubfield<ByteArrayWrapper, DynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>, DynamicByteArraySerialzing<ZT, ZYO, ZYD>>
  bytes() {
    return this.operationCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.operationCore.charset();
  }

}
