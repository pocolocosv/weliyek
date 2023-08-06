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
import java.util.function.Predicate;

import weliyek.amat.base.WkSzStructComponentCoreBase;
import weliyek.amat.base.WkSzDefinitionCore;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.OperationSubsegmentSettingsFactory;
import weliyek.amat.base.WkSzStruct;
import weliyek.amat.base.WkSzStructSubcomponent;
import weliyek.amat.base.input.CountingInputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.output.CountingOutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.basic.dynamic.sequence.VariableLengthSettings;
import weliyek.amat.basic.sequence.OptionalLengthSettings;
import weliyek.amat.basic.string.WkSzStringFromBytesDefinitionCore.ByteArrayFromStringDisaggregator;
import weliyek.amat2.protocol.filter.FieldTester;
import weliyek.ketza.util.array.ByteArrayWrapper;
import weliyek.ketza.util.array.PrimitiveArrayWrapper.ContigousIntsCounter;
import weliyek.ketza.util.array.VariableSizeByteArray;
import weliyek.ketza.util.array.VariableSizeByteArrayDeserializing;
import weliyek.ketza.util.array.VariableSizeByteArraySerializing;

public class StringWithVariableLengthBytes
    implements WkSzStringFromBytesDefinition<
                        StringWithVariableLengthBytesDeserializing,
                        StringWithVariableLengthBytesSerializing,
                        VariableSizeByteArray>
{

  public static WkSzStruct<
                      String,
                      VariableLengthSettings,
                      StringWithVariableLengthBytes,
                      StringWithVariableLengthBytesDeserializing,
                      InputBytestreamGeneralBase<?>,
                      OptionalLengthSettings,
                      StringWithVariableLengthBytes,
                      StringWithVariableLengthBytesSerializing,
                      OutputBytestreamGeneralBase<?>,
                      StringWithVariableLengthBytes>
  newPacketStructure(
    String label,
    String bytesLabel,
    int minimalLength,
    int maximalLength,
    Charset defaultCharset) {
    return new WkSzStruct<>(
                      label,
                      (pc) -> StringWithVariableLengthBytes.newCore(
                                    bytesLabel, minimalLength, maximalLength, defaultCharset, pc),
                      CountingInputBytestream::new,
                      CountingOutputBytestream::new);
  }

  public static WkSzDefinitionCore<
                      String,
                      VariableLengthSettings,?,?,
                      StringWithVariableLengthBytes,
                      StringWithVariableLengthBytesDeserializing,
                      InputBytestreamGeneralBase<?>,
                      OptionalLengthSettings,?,?,
                      StringWithVariableLengthBytes,
                      StringWithVariableLengthBytesSerializing,
                      OutputBytestreamGeneralBase<?>,
                      StringWithVariableLengthBytes,?>
  newCore(
    String bytesLabel,
    int minSize,
    int maxSize,
    Charset defaultCharset,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new StringWithVariableLengthBytes(bytesLabel, minSize, maxSize, defaultCharset, componentCore).definitionCore;
  }

  private final SimplifiedStringFromBytesCore<
                        VariableLengthSettings,
                        StringWithVariableLengthBytesDeserializing,
                        StringWithVariableLengthBytes,
                        OptionalLengthSettings,
                        StringWithVariableLengthBytesSerializing,
                        StringWithVariableLengthBytes,
                        VariableLengthSettings,
                        VariableSizeByteArrayDeserializing,
                        VariableSizeByteArray,
                        OperationSettings,
                        VariableSizeByteArraySerializing,
                        VariableSizeByteArray,
                        VariableSizeByteArray,
                        StringWithVariableLengthBytes> definitionCore;

  StringWithVariableLengthBytes(
    String bytesLabel,
    int minSize,
    int maxSize,
    Charset defaultCharset,
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new SimplifiedStringFromBytesCore<>(
                                    defaultCharset,
                                    componentCore,
                                    (i,xs,axb,xpc,dc) -> new StringWithVariableLengthBytesDeserializing(i,xs,axb,xpc,dc).operationCore,
                                    (i,y,ys,ayb,ypc,dc) -> new StringWithVariableLengthBytesSerializing(i,y,ys,ayb,ypc,dc).operationCore,
                                    bytesLabel,
                                    OperationSubsegmentSettingsFactory.parentSettingsReuser(), // reusing deserializing settings
                                    StringWithVariableLengthBytes::aggragateByteArray,
                                    OperationSettings::none,
                                    new VariableLengthBytesDissagregatorFromString(),
                                    (pc) -> VariableSizeByteArray.newCore(minSize, maxSize, pc),
                                    this);
  }

  private static String aggragateByteArray(StringWithVariableLengthBytesDeserializing deserializingStringOp) {
    Charset charset = deserializingStringOp.charset();
    ContigousIntsCounter zeroPaddingCounter = new ContigousIntsCounter(0);
    ByteArrayWrapper wrapper = deserializingStringOp.bytes().field().get().firstOperation().get().result().get().deserialized().get();
    wrapper.iterateAsIntsBackwardsWhileTrue(zeroPaddingCounter);
    int zeroPaddingLen = zeroPaddingCounter.count();
    int strBytesLen = wrapper.getLength() - zeroPaddingLen;
    return wrapper.convertToString(charset, 0, strBytesLen);
  }

  public static class VariableLengthBytesDissagregatorFromString
      extends ByteArrayFromStringDisaggregator<
                        StringWithVariableLengthBytesSerializing,
                        VariableSizeByteArray>
  {

    @Override
    protected Optional<Integer> requestedPrimitiveArrayLength(
      StringWithVariableLengthBytesSerializing stringSerializingOperation) {
      return stringSerializingOperation.settings().getOptionalLength();
    }

    @Override
    protected int requestedPrimitiveArrayPadding() {
      return 0;
    }

  }

  @Override
  public
  WkSzStructSubcomponent<StringWithVariableLengthBytesDeserializing, StringWithVariableLengthBytesSerializing, VariableSizeByteArray>
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
    Predicate<? super StringWithVariableLengthBytesDeserializing> test,
    String description) {
    return this.definitionCore.makeTester(test, description);
  }

  @Override
  public
  WkSzStructSubcomponent<StringWithVariableLengthBytesDeserializing, StringWithVariableLengthBytesSerializing, VariableSizeByteArray>
  bytes() {
    return this.definitionCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.definitionCore.charset();
  }

}