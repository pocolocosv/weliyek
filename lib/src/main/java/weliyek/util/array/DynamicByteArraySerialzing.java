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
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.number.WkSzNumberDefinition;
import weliyek.serialization.number.WkSzNumberWriter;

public class DynamicByteArraySerialzing<
                        ZT extends Number,
                        ZYO extends WkSzNumberWriter<ZT,WkSzOperationSettings,?,?,ZYD>,
                        ZYD extends WkSzNumberDefinition<ZT,?>>
    implements ByteArrayWriting<
                        WkSzOperationSettings,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        DynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>>,
               DynamicPrimitiveArraySerializing<
                        ByteArrayWrapper,
                        WkSzOperationSettings,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        DynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        ZT, ZYO, ZYD,
                        VariableSizeByteArraySerializing,
                        VariableSizeByteArray>
{

  final SimplifiedDynamicPrimitiveArraySerializingCore<
                        ByteArrayWrapper,
                        DynamicByteArraySerialzing<ZT,ZYO,ZYD>,
                        DynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        ZT, ZYO, ZYD,
                        VariableSizeByteArraySerializing,
                        VariableSizeByteArray> operationCore;

  DynamicByteArraySerialzing(
    int index,
    ByteArrayWrapper serializable,
    WkSzOperationSettings settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<
      ByteArrayWrapper,?,DynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,?,?,?> serializingfieldCore,
    SimplifiedDynamicPrimitiveArrayDefinitionCore<
      ByteArrayWrapper,?,?,DynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
      DynamicByteArraySerialzing<ZT,ZYO,ZYD>,ZT,?,?,ZYD,ZYO,?,?,?,
      VariableSizeByteArray,VariableSizeByteArraySerializing,?,
      ?> definitionCore) {
    this.operationCore = new SimplifiedDynamicPrimitiveArraySerializingCore<
                                ByteArrayWrapper,
                                DynamicByteArraySerialzing<ZT,ZYO,ZYD>,
                                DynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                                ZT, ZYO, ZYD,
                                VariableSizeByteArraySerializing,
                                VariableSizeByteArray>(
                                    index,
                                    serializable,
                                    settings,
                                    parentBytestream,
                                    serializingfieldCore,
                                    definitionCore,
                                    this);
  }

  @Override
  public ByteArrayWrapper serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public List<WkSzPacketWriterSubfield<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public DynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD> definition() {
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
  public WkSzPacketWriterField<ByteArrayWrapper,DynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,?>
  packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public WkSzPacketWriterSubfield<ZT, ZYD, ZYO> size() {
    return this.operationCore.size();
  }

  @Override
  public
  WkSzPacketWriterSubfield<ByteArrayWrapper, VariableSizeByteArray, VariableSizeByteArraySerializing>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

}
