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
import weliyek.amat.base.input.DeserializingField;
import weliyek.amat.base.input.DeserializingFieldCore;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.DeserializingSubfieldHandler;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.basic.number.NumberDefinition;
import weliyek.amat.basic.number.NumberDeserializing;

public class DynamicByteArrayDeserializing<
                        ZT extends Number,
                        ZXO extends NumberDeserializing<ZT,OperationSettings,?,?,ZXD>,
                        ZXD extends NumberDefinition<ZT,ZXO>>
    implements ByteArrayReading<
                        OperationSettings,
                        DeserializingRuntime<InputBytestream>,
                        DeserializingResult<ByteArrayWrapper>,
                        DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>>,
               DynamicPrimitiveArrayDeserializing<
                        ByteArrayWrapper,
                        OperationSettings,
                        DeserializingRuntime<InputBytestream>,
                        DeserializingResult<ByteArrayWrapper>,
                        DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        ZT,
                        ZXO,
                        ZXD,
                        VariableSizeByteArrayDeserializing,
                        VariableSizeByteArray>
{

  final SimplifiedDynamicPrimitiveArrayDeserializingCore<
                        ByteArrayWrapper,
                        DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,
                        DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        ZT, ZXO, ZXD,
                        VariableSizeByteArrayDeserializing,
                        VariableSizeByteArray> operationCore;

  DynamicByteArrayDeserializing(
    int index,
    OperationSettings settings,
    InputBytestreamGeneralBase<?> parentBytestream,
    DeserializingFieldCore<
      ByteArrayWrapper,?,DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,?,?,?> deserializingfieldCore,
    SimplifiedDynamicPrimitiveArrayDefinitionCore<
      ByteArrayWrapper,DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
      DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,?,?,ZT,ZXD,ZXO,?,?,?,
      VariableSizeByteArray,VariableSizeByteArrayDeserializing,?,?,?,
      ?> definitionCore) {
    this.operationCore = new SimplifiedDynamicPrimitiveArrayDeserializingCore<
                                ByteArrayWrapper,
                                DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,
                                DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                                ZT, ZXO, ZXD,
                                VariableSizeByteArrayDeserializing,
                                VariableSizeByteArray>(
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
  public List<DeserializingSubfieldHandler<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD> definition() {
    return this.operationCore.definition();
  }

  @Override
  public OperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public DeserializingRuntime<InputBytestream> dashboard() {
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
  public DeserializingField<ByteArrayWrapper,DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,?>
  packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public DeserializingSubfieldHandler<ZT, ZXD, ZXO> size() {
    return this.operationCore.size();
  }

  @Override
  public
  DeserializingSubfieldHandler<ByteArrayWrapper, VariableSizeByteArray, VariableSizeByteArrayDeserializing>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

}
