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
import java.util.function.IntFunction;
import java.util.function.Predicate;

import weliyek.amat.base.WkSzStructComponentCoreBase;
import weliyek.amat.base.WkSzDefinitionCore;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.WkSzStruct;
import weliyek.amat.base.ProtocolDefinitionFactory;
import weliyek.amat.base.WkSzStructSubcomponent;
import weliyek.amat.base.input.CountingInputBytestream;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.output.CountingOutputBytestream;
import weliyek.amat.base.output.OutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.basic.number.WkSzNumberDefinition;
import weliyek.amat.basic.number.WkSzNumberReader;
import weliyek.amat.basic.number.WkSzNumberWriter;
import weliyek.amat.basic.string.WkSzStringFromBytesDefinitionCore.ByteArrayFromStringDisaggregator;
import weliyek.amat2.protocol.filter.FieldTester;
import weliyek.ketza.util.array.ByteArrayWrapper;
import weliyek.ketza.util.array.DynamicByteArray;
import weliyek.ketza.util.array.DynamicByteArrayDeserializing;
import weliyek.ketza.util.array.DynamicByteArraySerialzing;
import weliyek.ketza.util.array.PrimitiveArrayWrapper.ContigousIntsCounter;

public class StringWithDynamicSizeBytes<
                        ZT extends Number,
                        ZXD extends WkSzNumberDefinition<ZT,ZXO>,
                        ZXO extends WkSzNumberReader<ZT,OperationSettings,?,?,ZXD>,
                        ZYD extends WkSzNumberDefinition<ZT,?>,
                        ZYO extends WkSzNumberWriter<ZT,OperationSettings,?,?,ZYD>,
                        ZD extends WkSzNumberDefinition<ZT,ZXO>>
    implements WkSzStringFromBytesDefinition<
                        StringWithDynamicSizeBytesDeserializing<ZT,ZXD,ZXO>,
                        StringWithDynamicSizeBytesSerializing<ZT,ZYD,ZYO>,
                        DynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>
{

  public static <ZX extends Number,
                 ZXD extends WkSzNumberDefinition<ZX,ZXO>,
                 ZXO extends WkSzNumberReader<ZX,OperationSettings,?,?,ZXD>,
                 ZYD extends WkSzNumberDefinition<ZX,?>,
                 ZYO extends WkSzNumberWriter<ZX,OperationSettings,?,?,ZYD>,
                 ZD extends WkSzNumberDefinition<ZX,ZXO>>
  WkSzStruct<String,
                  OperationSettings,
                  StringWithDynamicSizeBytes<ZX,ZXD,ZXO,?,?,? extends ZXD>,
                  StringWithDynamicSizeBytesDeserializing<ZX,ZXD,ZXO>,
                  InputBytestreamGeneralBase<?>,
                  OperationSettings,
                  StringWithDynamicSizeBytes<ZX,?,?,ZYD,ZYO,? extends ZYD>,
                  StringWithDynamicSizeBytesSerializing<ZX,ZYD,ZYO>,
                  OutputBytestreamGeneralBase<?>,
                  StringWithDynamicSizeBytes<ZX,ZXD,ZXO,ZYD,ZYO,ZD>>
  newPacketStructure(
    String label,
    String bytesLabel,
    String sizeLabel,
    String arrayLabel,
    int minLength,
    int maxLength,
    Charset defaultCharset,
    IntFunction<ZX> wrapperSizeGetter,
    ProtocolDefinitionFactory<
      ZX,OperationSettings,ZXD,ZXO,InputBytestreamGeneralBase<? extends InputBytestream>,OperationSettings,
      ZYD,ZYO,OutputBytestreamGeneralBase<? extends OutputBytestream>,ZD> sizeDefinitionFactory) {
    return new WkSzStruct<>(
                  label,
                  (pc) -> StringWithDynamicSizeBytes.newCore(
                                pc, bytesLabel, sizeLabel, arrayLabel, minLength, maxLength, defaultCharset, wrapperSizeGetter, sizeDefinitionFactory),
                  CountingInputBytestream::new,
                  CountingOutputBytestream::new);
  }

  public static <ZX extends Number,
                 ZXD extends WkSzNumberDefinition<ZX,ZXO>,
                 ZXO extends WkSzNumberReader<ZX,OperationSettings,?,?,ZXD>,
                 ZYD extends WkSzNumberDefinition<ZX,?>,
                 ZYO extends WkSzNumberWriter<ZX,OperationSettings,?,?,ZYD>,
                 ZD extends WkSzNumberDefinition<ZX,ZXO>>
  WkSzDefinitionCore<
                        String,
                        OperationSettings,?,?,
                        StringWithDynamicSizeBytes<ZX,ZXD,ZXO,?,?,? extends ZXD>,
                        StringWithDynamicSizeBytesDeserializing<ZX,ZXD,ZXO>,
                        InputBytestreamGeneralBase<?>,
                        OperationSettings,?,?,
                        StringWithDynamicSizeBytes<ZX,?,?,ZYD,ZYO,? extends ZYD>,
                        StringWithDynamicSizeBytesSerializing<ZX,ZYD,ZYO>,
                        OutputBytestreamGeneralBase<?>,
                        StringWithDynamicSizeBytes<ZX,ZXD,ZXO,ZYD,ZYO,ZD>,?>
  newCore(
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore,
    String bytesLabel,
    String sizeLabel,
    String arrayLabel,
    int minLength,
    int maxLength,
    Charset defaultCharset,
    IntFunction<ZX> wrapperSizeGetter,
    ProtocolDefinitionFactory<
      ZX,OperationSettings,ZXD,ZXO,InputBytestreamGeneralBase<? extends InputBytestream>,OperationSettings,
      ZYD,ZYO,OutputBytestreamGeneralBase<? extends OutputBytestream>,ZD> sizeDefinitionFactory) {
    return new StringWithDynamicSizeBytes<ZX,ZXD,ZXO,ZYD,ZYO,ZD>(
                        defaultCharset,
                        componentCore,
                        bytesLabel,
                        sizeLabel,
                        wrapperSizeGetter,
                        arrayLabel,
                        minLength,
                        maxLength,
                        sizeDefinitionFactory).definitionCore;
  }

  private final SimplifiedStringFromBytesCore<
                        OperationSettings,
                        StringWithDynamicSizeBytesDeserializing<ZT,ZXD,ZXO>,
                        StringWithDynamicSizeBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        OperationSettings,
                        StringWithDynamicSizeBytesSerializing<ZT,ZYD,ZYO>,
                        StringWithDynamicSizeBytes<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        OperationSettings,
                        DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,
                        DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        OperationSettings,
                        DynamicByteArraySerialzing<ZT,ZYO,ZYD>,
                        DynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        DynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>,
                        StringWithDynamicSizeBytes<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>
                            definitionCore;

  private StringWithDynamicSizeBytes(
    Charset defaultCharset,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore,
    String bytesLabel,
    String szieLabel,
    IntFunction<ZT> wrapperSizeGetter,
    String arrayLabel,
    int minLength,
    int maxLength,
    ProtocolDefinitionFactory<
      ZT,OperationSettings,ZXD,ZXO,InputBytestreamGeneralBase<? extends InputBytestream>,OperationSettings,ZYD,ZYO,
      OutputBytestreamGeneralBase<? extends OutputBytestream>,ZD> sizeDefinitionFactory) {
    this.definitionCore = new SimplifiedStringFromBytesCore<
                                  OperationSettings,
                                  StringWithDynamicSizeBytesDeserializing<ZT,ZXD,ZXO>,
                                  StringWithDynamicSizeBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                                  OperationSettings,
                                  StringWithDynamicSizeBytesSerializing<ZT,ZYD,ZYO>,
                                  StringWithDynamicSizeBytes<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                                  OperationSettings,
                                  DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,
                                  DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                                  OperationSettings,
                                  DynamicByteArraySerialzing<ZT,ZYO,ZYD>,
                                  DynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                                  DynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>,
                                  StringWithDynamicSizeBytes<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>(
                                      defaultCharset,
                                      componentCore,
                                      (i,xs,axb,xpc,dc) -> new StringWithDynamicSizeBytesDeserializing<ZT,ZXD,ZXO>(i,xs,axb,xpc,dc).operationCore,
                                      (i,y,ys,ayb,ypc,dc) -> new StringWithDynamicSizeBytesSerializing<ZT,ZYD,ZYO>(i,y,ys,ayb,ypc,dc).operationCore,
                                      bytesLabel,
                                      OperationSettings::none,
                                      StringWithDynamicSizeBytes::aggragateByteArray,
                                      OperationSettings::none,
                                      new BytesFromDynamicStringDisaggregator<ZT,ZYD,ZYO>(),
                                      (pc) -> DynamicByteArray.<ZT,ZXD,ZXO,ZYD,ZYO,ZD>newCore(
                                          pc, szieLabel, wrapperSizeGetter, arrayLabel,
                                          minLength, maxLength, sizeDefinitionFactory),
                                      this);
  }

  private static <ZX extends Number,
                  ZXD extends WkSzNumberDefinition<ZX,ZXO>,
                  ZXO extends WkSzNumberReader<ZX,OperationSettings,?,?,ZXD>>
  String aggragateByteArray(StringWithDynamicSizeBytesDeserializing<ZX,ZXD,ZXO> deserializingStringOp) {
    Charset charset = deserializingStringOp.charset();
    ContigousIntsCounter zeroPaddingCounter = new ContigousIntsCounter(0);
    ByteArrayWrapper wrapper = deserializingStringOp.bytes().field().get().firstOperation().get().result().get().deserialized().get();
    wrapper.iterateAsIntsBackwardsWhileTrue(zeroPaddingCounter);
    int zeroPaddingLen = zeroPaddingCounter.count();
    int strBytesLen = wrapper.getLength() - zeroPaddingLen;
    return wrapper.convertToString(charset, 0, strBytesLen);
  }

  public static
  class BytesFromDynamicStringDisaggregator<
                        ZY extends Number,
                        ZYD extends WkSzNumberDefinition<ZY,?>,
                        ZYO extends WkSzNumberWriter<ZY,OperationSettings,?,?,ZYD>>
      extends ByteArrayFromStringDisaggregator<
                        StringWithDynamicSizeBytesSerializing<ZY,ZYD,ZYO>,
                        DynamicByteArray<ZY,?,?,ZYD,ZYO,? extends ZYD>>
  {

    @Override
    protected Optional<Integer> requestedPrimitiveArrayLength(
      StringWithDynamicSizeBytesSerializing<ZY,ZYD,ZYO> stringSerializingOperation) {
      return Optional.empty();
    }

    @Override
    protected int requestedPrimitiveArrayPadding() {
      return 0;
    }

  }

  @Override
  public
  WkSzStructSubcomponent<
              StringWithDynamicSizeBytesDeserializing<ZT,ZXD,ZXO>,
              StringWithDynamicSizeBytesSerializing<ZT,ZYD,ZYO>,
              DynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>
  primitiveArray() {
    return this.definitionCore.primitiveArray();
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public Class<String> rxClass() {
    return String.class;
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?, ?>
  makeTester(
    Predicate<? super StringWithDynamicSizeBytesDeserializing<ZT, ZXD, ZXO>> test,
    String description) {
    return this.definitionCore.makeTester(test, description);
  }

  @Override
  public
  WkSzStructSubcomponent<
            StringWithDynamicSizeBytesDeserializing<ZT,ZXD,ZXO>,
            StringWithDynamicSizeBytesSerializing<ZT,ZYD,ZYO>,
            DynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>
  bytes() {
    return this.definitionCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.definitionCore.charset();
  }

  @Override
  public String toString() {
    return this.definitionCore.name();
  }

}
