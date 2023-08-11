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
import java.util.function.IntFunction;
import java.util.function.Predicate;

import weliyek.amat.base.WkSzOperationSettings;
import weliyek.amat.base.input.WkSzCountingInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.base.output.WkSzCountingOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.basic.number.WkSzNumberDefinition;
import weliyek.amat.basic.number.WkSzNumberReader;
import weliyek.amat.basic.number.WkSzNumberWriter;
import weliyek.amat2.protocol.filter.FieldTester;
import weliyek.serialization.ProtocolDefinitionFactory;
import weliyek.serialization.WkSzStruct;
import weliyek.serialization.WkSzStructSubcomponent;
import weliyek.serialization.base.WkSzDefinitionCore;
import weliyek.serialization.base.WkSzStructComponentCoreBase;

public class DynamicByteArray<
                        ZT extends Number,
                        ZXD extends WkSzNumberDefinition<ZT,ZXO>,
                        ZXO extends WkSzNumberReader<ZT,WkSzOperationSettings,?,?,ZXD>,
                        ZYD extends WkSzNumberDefinition<ZT,?>,
                        ZYO extends WkSzNumberWriter<ZT,WkSzOperationSettings,?,?,ZYD>,
                        ZD extends WkSzNumberDefinition<ZT,ZXO>>
    implements WkSzByteArrayDefinition<
                        DynamicByteArrayDeserializing<ZT,ZXO,ZXD>>,
               WkSzDynamicPrimitiveArrayDefinition<
                        ByteArrayWrapper,
                        DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,
                        DynamicByteArraySerialzing<ZT,ZYO,ZYD>,
                        ZD,
                        VariableSizeByteArray>
{
  public static <ZX extends Number,
                 ZXD extends WkSzNumberDefinition<ZX,ZXO>,
                 ZXO extends WkSzNumberReader<ZX,WkSzOperationSettings,?,?,ZXD>,
                 ZYD extends WkSzNumberDefinition<ZX,?>,
                 ZYO extends WkSzNumberWriter<ZX,WkSzOperationSettings,?,?,ZYD>,
                 ZD extends WkSzNumberDefinition<ZX,ZXO>>
  WkSzStruct<ByteArrayWrapper,
                  WkSzOperationSettings,
                  DynamicByteArray<ZX,ZXD,ZXO,?,?,? extends ZXD>,
                  DynamicByteArrayDeserializing<ZX,ZXO,ZXD>,
                  WkSzInputBytestreamBase<?>,
                  WkSzOperationSettings,
                  DynamicByteArray<ZX,?,?,ZYD,ZYO,? extends ZYD>,
                  DynamicByteArraySerialzing<ZX,ZYO,ZYD>,
                  WkSzOutputBytestreamBase<?>,
                  DynamicByteArray<ZX,ZXD,ZXO,ZYD,ZYO,ZD>>
  newPacketStructure(
    String label,
    String sizeLabel,
    String bytearrayLabel,
    int minLength,
    int maxLength,
    IntFunction<ZX> sizeWrapper,
    ProtocolDefinitionFactory<
      ZX,WkSzOperationSettings,ZXD,ZXO,WkSzInputBytestreamBase<?>,
      WkSzOperationSettings,ZYD,ZYO,WkSzOutputBytestreamBase<?>,ZD>  sizeDefinition)
  {
    return new WkSzStruct<ByteArrayWrapper,
        WkSzOperationSettings,
        DynamicByteArray<ZX,ZXD,ZXO,?,?,? extends ZXD>,
        DynamicByteArrayDeserializing<ZX,ZXO,ZXD>,
        WkSzInputBytestreamBase<?>,
        WkSzOperationSettings,
        DynamicByteArray<ZX,?,?,ZYD,ZYO,? extends ZYD>,
        DynamicByteArraySerialzing<ZX,ZYO,ZYD>,
        WkSzOutputBytestreamBase<?>,
        DynamicByteArray<ZX,ZXD,ZXO,ZYD,ZYO,ZD>>(
                  label,
                  (pc) -> DynamicByteArray.newCore(
                              pc, sizeLabel, sizeWrapper, bytearrayLabel, minLength, maxLength, sizeDefinition),
                  WkSzCountingInputBytestream::new,
                  WkSzCountingOutputBytestream::new);
  }

  public static <ZX extends Number,
                 ZXD extends WkSzNumberDefinition<ZX,ZXO>,
                 ZXO extends WkSzNumberReader<ZX,WkSzOperationSettings,?,?,ZXD>,
                 ZYD extends WkSzNumberDefinition<ZX,?>,
                 ZYO extends WkSzNumberWriter<ZX,WkSzOperationSettings,?,?,ZYD>,
                 ZD extends WkSzNumberDefinition<ZX,ZXO>>
  WkSzDefinitionCore<ByteArrayWrapper,
                        WkSzOperationSettings,?,?,
                        DynamicByteArray<ZX,ZXD,ZXO,?,?,? extends ZXD>,
                        DynamicByteArrayDeserializing<ZX,ZXO,ZXD>,
                        WkSzInputBytestreamBase<?>,
                        WkSzOperationSettings,?,?,
                        DynamicByteArray<ZX,?,?,ZYD,ZYO,? extends ZYD>,
                        DynamicByteArraySerialzing<ZX,ZYO,ZYD>,
                        WkSzOutputBytestreamBase<?>,
                        DynamicByteArray<ZX,ZXD,ZXO,ZYD,ZYO,ZD>,?>
  newCore(
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore,
    String sizeLabel,
    IntFunction<ZX> wrapperSizeGetter,
    String arrayLabel,
    int minLength,
    int maxLength,
    ProtocolDefinitionFactory<
      ZX,WkSzOperationSettings,ZXD,ZXO,WkSzInputBytestreamBase<?>,
      WkSzOperationSettings,ZYD,ZYO,WkSzOutputBytestreamBase<?>,ZD> sizeDefinitionFactory)
  {
    return new DynamicByteArray<ZX,ZXD,ZXO,ZYD,ZYO,ZD>(componentCore, sizeLabel, wrapperSizeGetter, arrayLabel, minLength, maxLength, sizeDefinitionFactory).definitionCore;
  }

  private final SimplifiedDynamicPrimitiveArrayDefinitionCore<
                        ByteArrayWrapper,
                        DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,
                        DynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        DynamicByteArraySerialzing<ZT,ZYO,ZYD>,
                        ZT, ZXD, ZXO, ZYD, ZYO, ZD,
                        VariableSizeByteArray,
                        VariableSizeByteArrayDeserializing,
                        VariableSizeByteArray,
                        VariableSizeByteArraySerializing,
                        VariableSizeByteArray,
                        DynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>> definitionCore;

  DynamicByteArray(WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore,
    String sizeLabel,
    IntFunction<ZT> wrapperSizeGetter,
    String arrayLabel,
    int minLength,
    int maxLength,
    ProtocolDefinitionFactory<
      ZT,WkSzOperationSettings,ZXD,ZXO,WkSzInputBytestreamBase<?>,WkSzOperationSettings,
      ZYD,ZYO,WkSzOutputBytestreamBase<?>,ZD> sizeComponentDefinitionFactory) {
    this.definitionCore = new SimplifiedDynamicPrimitiveArrayDefinitionCore<
                                ByteArrayWrapper,
                                DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                                DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,
                                DynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                                DynamicByteArraySerialzing<ZT,ZYO,ZYD>,
                                ZT, ZXD, ZXO, ZYD, ZYO, ZD,
                                VariableSizeByteArray,
                                VariableSizeByteArrayDeserializing,
                                VariableSizeByteArray,
                                VariableSizeByteArraySerializing,
                                VariableSizeByteArray,
                                DynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>(
                                    sizeLabel,
                                    wrapperSizeGetter,
                                    sizeComponentDefinitionFactory,
                                    arrayLabel,
                                    (pc) -> VariableSizeByteArray.newCore(minLength, maxLength, pc),
                                    (i,xs,axb,xkc,dc) -> new DynamicByteArrayDeserializing<ZT,ZXO,ZXD>(i,xs,axb,xkc,dc).operationCore,
                                    (i,y,ys,ayb,ykc,dc) -> new DynamicByteArraySerialzing<ZT,ZYO,ZYD>(i,y,ys,ayb,ykc,dc).operationCore,
                                    componentCore,
                                    this,
                                    ByteArrayWrapper.class);
  }

  @Override
  public Class<ByteArrayWrapper> rxClass() {
    return ByteArrayWrapper.class;
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?, ?> makeTester(
    Predicate<? super DynamicByteArrayDeserializing<ZT, ZXO, ZXD>> test,
    String description) {
    return this.definitionCore.makeTester(test, description);
  }

  @Override
  public
      WkSzStructSubcomponent<DynamicByteArrayDeserializing<ZT, ZXO, ZXD>, DynamicByteArraySerialzing<ZT, ZYO, ZYD>, ZD>
      size() {
    return this.definitionCore.size();
  }

  @Override
  public
      WkSzStructSubcomponent<DynamicByteArrayDeserializing<ZT, ZXO, ZXD>, DynamicByteArraySerialzing<ZT, ZYO, ZYD>, VariableSizeByteArray>
      variableSequence() {
    return this.definitionCore.variableSequence();
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public String toString() {
    return this.definitionCore.name();
  }

}
