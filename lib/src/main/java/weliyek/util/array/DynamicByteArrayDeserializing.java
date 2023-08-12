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

import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketReaderField;
import weliyek.serialization.WkSzPacketReaderFieldCore;
import weliyek.serialization.WkSzPacketReaderSubfield;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.number.WkSzNumberDefinition;
import weliyek.serialization.number.WkSzNumberReader;

public class DynamicByteArrayDeserializing<
                        ZT extends Number,
                        ZXO extends WkSzNumberReader<ZT,WkSzOperationSettings,?,?,ZXD>,
                        ZXD extends WkSzNumberDefinition<ZT,ZXO>>
    implements WkSzByteArrayReader<
                        WkSzOperationSettings,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<WkByteArray>,
                        DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>>,
               DynamicPrimitiveArrayDeserializing<
                        WkByteArray,
                        WkSzOperationSettings,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<WkByteArray>,
                        DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        ZT,
                        ZXO,
                        ZXD,
                        WkSzVariableSizeByteArrayReader,
                        WkSzVariableSizeByteArray>
{

  final SimplifiedDynamicPrimitiveArrayDeserializingCore<
                        WkByteArray,
                        DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,
                        DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        ZT, ZXO, ZXD,
                        WkSzVariableSizeByteArrayReader,
                        WkSzVariableSizeByteArray> operationCore;

  DynamicByteArrayDeserializing(
    int index,
    WkSzOperationSettings settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<
      WkByteArray,?,DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,?,?,?> deserializingfieldCore,
    SimplifiedDynamicPrimitiveArrayDefinitionCore<
      WkByteArray,DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
      DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,?,?,ZT,ZXD,ZXO,?,?,?,
      WkSzVariableSizeByteArray,WkSzVariableSizeByteArrayReader,?,?,?,
      ?> definitionCore) {
    this.operationCore = new SimplifiedDynamicPrimitiveArrayDeserializingCore<
                                WkByteArray,
                                DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,
                                DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                                ZT, ZXO, ZXD,
                                WkSzVariableSizeByteArrayReader,
                                WkSzVariableSizeByteArray>(
                                    index,
                                    settings,
                                    parentBytestream,
                                    deserializingfieldCore,
                                    definitionCore,
                                    this);
  }

  /*
  public DynamicByteArrayDeserializing(
    int i,
    OperationSettings xs,
    InputBytestream<?> axb,
    DeserializingFieldCore<ByteArrayWrapper, ?, ? extends DynamicByteArray<ZX, ZXO, ?, ?, ZD>, ?, ?, ?> xkc,
    SimplifiedDynamicPrimitiveArrayDefinitionCore<ByteArrayWrapper, DynamicByteArrayDeserializing<ZX, ZXO, ZD>, DynamicByteArraySerialzing<ZY, ZYO, ZD>, ZX, ZXO, ZY, ZYO, ZD, VariableSizeByteArrayDeserializing, VariableSizeByteArraySerializing, VariableSizeByteArray, DynamicByteArray<ZX, ZXO, ZY, ZYO, ZD>> dc) {
    // TODO Auto-generated constructor stub
  }
  */

  @Override
  public List<WkSzPacketReaderSubfield<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD> definition() {
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
  public Optional<WkSzReadingResult<WkByteArray>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSzPacketReaderField<WkByteArray,DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,?>
  packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public WkSzPacketReaderSubfield<ZT, ZXD, ZXO> size() {
    return this.operationCore.size();
  }

  @Override
  public
  WkSzPacketReaderSubfield<WkByteArray, WkSzVariableSizeByteArray, WkSzVariableSizeByteArrayReader>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

}
