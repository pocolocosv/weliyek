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
import java.util.function.IntFunction;
import java.util.function.Predicate;

import weliyek.serialization.ProtocolDefinitionFactory;
import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSzDefinitionCore;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzStruct;
import weliyek.serialization.WkSzStructComponentCoreBase;
import weliyek.serialization.WkSzStructSubcomponent;
import weliyek.serialization.filter.FieldTester;
import weliyek.serialization.number.WkSzNumberDefinition;
import weliyek.serialization.number.WkSzNumberReader;
import weliyek.serialization.number.WkSzNumberWriter;
import weliyek.serialization.string.WkSzStringFromBytesDefinitionCore.ByteArrayFromStringDisaggregator;
import weliyek.serialization.util.array.ByteArrayWrapper;
import weliyek.serialization.util.array.DynamicByteArray;
import weliyek.serialization.util.array.DynamicByteArrayDeserializing;
import weliyek.serialization.util.array.DynamicByteArraySerialzing;
import weliyek.serialization.util.array.PrimitiveArrayWrapper.ContigousIntsCounter;

public class StringWithDynamicSizeBytes<
                        ZT extends Number,
                        ZXD extends WkSzNumberDefinition<ZT,ZXO>,
                        ZXO extends WkSzNumberReader<ZT,WkSzOperationSettings,?,?,ZXD>,
                        ZYD extends WkSzNumberDefinition<ZT,?>,
                        ZYO extends WkSzNumberWriter<ZT,WkSzOperationSettings,?,?,ZYD>,
                        ZD extends WkSzNumberDefinition<ZT,ZXO>>
    implements WkSzStringFromBytesDefinition<
                        StringWithDynamicSizeBytesDeserializing<ZT,ZXD,ZXO>,
                        StringWithDynamicSizeBytesSerializing<ZT,ZYD,ZYO>,
                        DynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>
{

  public static <ZX extends Number,
                 ZXD extends WkSzNumberDefinition<ZX,ZXO>,
                 ZXO extends WkSzNumberReader<ZX,WkSzOperationSettings,?,?,ZXD>,
                 ZYD extends WkSzNumberDefinition<ZX,?>,
                 ZYO extends WkSzNumberWriter<ZX,WkSzOperationSettings,?,?,ZYD>,
                 ZD extends WkSzNumberDefinition<ZX,ZXO>>
  WkSzStruct<String,
                  WkSzOperationSettings,
                  StringWithDynamicSizeBytes<ZX,ZXD,ZXO,?,?,? extends ZXD>,
                  StringWithDynamicSizeBytesDeserializing<ZX,ZXD,ZXO>,
                  WkSzInputBytestreamBase<?>,
                  WkSzOperationSettings,
                  StringWithDynamicSizeBytes<ZX,?,?,ZYD,ZYO,? extends ZYD>,
                  StringWithDynamicSizeBytesSerializing<ZX,ZYD,ZYO>,
                  WkSzOutputBytestreamBase<?>,
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
      ZX,WkSzOperationSettings,ZXD,ZXO,WkSzInputBytestreamBase<? extends WkSzInputBytestream>,WkSzOperationSettings,
      ZYD,ZYO,WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,ZD> sizeDefinitionFactory) {
    return new WkSzStruct<>(
                  label,
                  (pc) -> StringWithDynamicSizeBytes.newCore(
                                pc, bytesLabel, sizeLabel, arrayLabel, minLength, maxLength, defaultCharset, wrapperSizeGetter, sizeDefinitionFactory),
                  WkSzCountingInputBytestream::new,
                  WkSzCountingOutputBytestream::new);
  }

  public static <ZX extends Number,
                 ZXD extends WkSzNumberDefinition<ZX,ZXO>,
                 ZXO extends WkSzNumberReader<ZX,WkSzOperationSettings,?,?,ZXD>,
                 ZYD extends WkSzNumberDefinition<ZX,?>,
                 ZYO extends WkSzNumberWriter<ZX,WkSzOperationSettings,?,?,ZYD>,
                 ZD extends WkSzNumberDefinition<ZX,ZXO>>
  WkSzDefinitionCore<
                        String,
                        WkSzOperationSettings,?,?,
                        StringWithDynamicSizeBytes<ZX,ZXD,ZXO,?,?,? extends ZXD>,
                        StringWithDynamicSizeBytesDeserializing<ZX,ZXD,ZXO>,
                        WkSzInputBytestreamBase<?>,
                        WkSzOperationSettings,?,?,
                        StringWithDynamicSizeBytes<ZX,?,?,ZYD,ZYO,? extends ZYD>,
                        StringWithDynamicSizeBytesSerializing<ZX,ZYD,ZYO>,
                        WkSzOutputBytestreamBase<?>,
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
      ZX,WkSzOperationSettings,ZXD,ZXO,WkSzInputBytestreamBase<? extends WkSzInputBytestream>,WkSzOperationSettings,
      ZYD,ZYO,WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,ZD> sizeDefinitionFactory) {
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
                        WkSzOperationSettings,
                        StringWithDynamicSizeBytesDeserializing<ZT,ZXD,ZXO>,
                        StringWithDynamicSizeBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        WkSzOperationSettings,
                        StringWithDynamicSizeBytesSerializing<ZT,ZYD,ZYO>,
                        StringWithDynamicSizeBytes<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        WkSzOperationSettings,
                        DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,
                        DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        WkSzOperationSettings,
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
      ZT,WkSzOperationSettings,ZXD,ZXO,WkSzInputBytestreamBase<? extends WkSzInputBytestream>,WkSzOperationSettings,ZYD,ZYO,
      WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,ZD> sizeDefinitionFactory) {
    this.definitionCore = new SimplifiedStringFromBytesCore<
                                  WkSzOperationSettings,
                                  StringWithDynamicSizeBytesDeserializing<ZT,ZXD,ZXO>,
                                  StringWithDynamicSizeBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                                  WkSzOperationSettings,
                                  StringWithDynamicSizeBytesSerializing<ZT,ZYD,ZYO>,
                                  StringWithDynamicSizeBytes<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                                  WkSzOperationSettings,
                                  DynamicByteArrayDeserializing<ZT,ZXO,ZXD>,
                                  DynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                                  WkSzOperationSettings,
                                  DynamicByteArraySerialzing<ZT,ZYO,ZYD>,
                                  DynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                                  DynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>,
                                  StringWithDynamicSizeBytes<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>(
                                      defaultCharset,
                                      componentCore,
                                      (i,xs,axb,xpc,dc) -> new StringWithDynamicSizeBytesDeserializing<ZT,ZXD,ZXO>(i,xs,axb,xpc,dc).operationCore,
                                      (i,y,ys,ayb,ypc,dc) -> new StringWithDynamicSizeBytesSerializing<ZT,ZYD,ZYO>(i,y,ys,ayb,ypc,dc).operationCore,
                                      bytesLabel,
                                      WkSzOperationSettings::none,
                                      StringWithDynamicSizeBytes::aggragateByteArray,
                                      WkSzOperationSettings::none,
                                      new BytesFromDynamicStringDisaggregator<ZT,ZYD,ZYO>(),
                                      (pc) -> DynamicByteArray.<ZT,ZXD,ZXO,ZYD,ZYO,ZD>newCore(
                                          pc, szieLabel, wrapperSizeGetter, arrayLabel,
                                          minLength, maxLength, sizeDefinitionFactory),
                                      this);
  }

  private static <ZX extends Number,
                  ZXD extends WkSzNumberDefinition<ZX,ZXO>,
                  ZXO extends WkSzNumberReader<ZX,WkSzOperationSettings,?,?,ZXD>>
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
                        ZYO extends WkSzNumberWriter<ZY,WkSzOperationSettings,?,?,ZYD>>
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
