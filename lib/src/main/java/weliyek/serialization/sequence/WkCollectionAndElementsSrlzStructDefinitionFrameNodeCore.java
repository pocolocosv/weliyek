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
package weliyek.serialization.sequence;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import weliyek.serialization.WkSzPacketWriteDisaggregator;
import weliyek.serialization.WkOperationSettingsFactory;
import weliyek.serialization.WkSzPacketReaderOperationCoreFactory;
import weliyek.serialization.WkSzPacketWriterOperationCoreFactory;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkAggregatorSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNode;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzInputPacketDecoderFrameNode;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketEncoderFrameNode;
import weliyek.serialization.WkDecodingResultSrlzPacketOperationData;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSequenceDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructSubcomponentFrameNode;
import weliyek.serialization.WkSrlzStructSubcomponentFrameNodeCore;
import weliyek.serialization.WkEncodingResultSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationCtrl;

public abstract class WkCollectionAndElementsSrlzStructDefinitionFrameNodeCore<
                        T extends Collection<ET>,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XB extends WkSzInputBytestream,
                        XBC extends WkSzInputBytestreamBase<? extends XB>,
                        XQC extends WkSequenceDecodingRuntimeSrlzPacketOperationCtrl<XB,XBC,?>,
                        XR extends WkDecodingResultSrlzPacketOperationData<T>,
                        XD extends WkCollectionAndElementsSrlzStructDefinitionFrameNode<T,XO,?,ET,?>,
                        XO extends WkCollectionAndElementsSrlzInputPacketDecoderFrameNode<
                                        T,XS,? extends WkDecodingRuntimeSrlzPacketOperationData<XB>,XR,XD,ET,EXD,EXO>,
                        AXB extends WkSzInputBytestreamBase<?>,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YB extends WkSzOutputBytestream,
                        YBC extends WkSzOutputBytestreamBase<? extends YB>,
                        YQC extends WkEncodingRuntimeSrlzPacketOperationCtrl<YB,YBC,?>,
                        YR extends WkEncodingResultSrlzPacketOperationData,
                        YD extends WkCollectionAndElementsSrlzStructDefinitionFrameNode<T,?,YO,ET,?>,
                        YO extends WkCollectionAndElementsSrlzOutputPacketEncoderFrameNode<
                                        T,YS,? extends WkEncodingRuntimeSrlzPacketOperationData<YB>,YR,YD,ET,EYD,EYO>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        ET,
                        EXS extends WkSettingsSrlzPacketOperationData,
                        EXD extends WkSrlzStructDefinitionFrameNode<ET,?>,
                        EXO extends WkSrlzInputPacketDecoderFrameNode<ET,EXS,?,?,EXD>,
                        EYS extends WkSettingsSrlzPacketOperationData,
                        EYD extends WkSrlzStructDefinitionFrameNode<ET,?>,
                        EYO extends WkSrlzOutputPacketEncoderFrameNode<ET,EYS,?,?,EYD>,
                        ED extends WkSrlzStructDefinitionFrameNode<ET,?>,
                        D extends WkCollectionAndElementsSrlzStructDefinitionFrameNode<T,XO,YO,ET,ED>,
                        DC extends WkCollectionAndElementsSrlzStructDefinitionFrameNodeCore<
                                        T,XS,XB,XBC,XQC,XR,XD,XO,AXB,
                                        YS,YB,YBC,YQC,YR,YD,YO,AYB,
                                        ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,D,?>>
    extends WkAggregatorSrlzStructDefinitionFrameNodeCore<
                        T, XS, XB, XBC, XQC, XR, XD, XO, AXB,
                        YS, YB, YBC, YQC, YR, YD, YO, AYB, D, DC>
    implements WkCollectionAndElementsSrlzStructDefinitionFrameNode<T, XO, YO, ET, ED>
{

  final WkSrlzStructSubcomponentFrameNodeCore<ET,EXS,EXD,EXO,T,XBC,XD,XO,EYS,EYD,EYO,YBC,YD,YO,ED,D>
                        elementComponent;
  final Function<XO, T> collectionSerializingFactory;

  protected WkCollectionAndElementsSrlzStructDefinitionFrameNodeCore(
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    Function<AXB, XQC> rxRuntimeFactory,
    BiFunction<XO, T, XR> rxResultFactory,
    WkSzPacketReaderOperationCoreFactory<T, XS, XD, DC, XO, AXB> readingOpFactory,
    Function<AYB, YQC> txRuntimeFactory,
    Function<YO, YR> txResultFactory,
    WkSzPacketWriterOperationCoreFactory<T, YS, YD, DC, YO, AYB> writingOpFactory,
    String elementsLabel,
    ToIntFunction<? super XO> elementsDeserializingNumOfOps,
    WkOperationSettingsFactory<XO, EXS> elementsRxSettingsFactory,
    WkOperationSettingsFactory<YO, EYS> elementsTxSettingsFactory,
    WkSzPacketWriteDisaggregator<ET,EYD,T,YO> elementsDisaggregator,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ET,EXS,EXD,EXO,XBC,EYS,EYD,EYO,YBC,ED> elementsDefinitionFactory,
    Function<XO,T> collectionSerializingFactory,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          componentCore,
          rxRuntimeFactory,
          rxResultFactory,
          readingOpFactory,
          txRuntimeFactory,
          txResultFactory,
          writingOpFactory,
          definitionBody,
          serializableClass);
    this.collectionSerializingFactory = Objects.requireNonNull(collectionSerializingFactory);
    this.elementComponent = this.<ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>addSubcomponent(
                                      elementsLabel,
                                      Optional.empty(),
                                      elementsDeserializingNumOfOps,
                                      elementsRxSettingsFactory,
                                      Optional.empty(),
                                      (yo) -> yo.serializable().size(),
                                      elementsTxSettingsFactory,
                                      WkCollectionAndElementsSrlzStructDefinitionFrameNodeCore::disaggregateCollection,
                                      false,
                                      elementsDefinitionFactory);
  }

  private static <T extends Collection<ET>,
                  YO extends WkCollectionAndElementsSrlzOutputPacketEncoderFrameNode<T,?,?,?,?,ET,EYD,?>,
                  ET,
                  EYD extends WkSrlzStructDefinitionFrameNode<ET,?>>
  ET disaggregateCollection(
    WkSrlzOutputPacketFieldFrameNode<ET,EYD,?> serializingField,
    YO collectionWritingOp,
    int index) {
    if (collectionWritingOp.serializableAsList().size() <= index) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return collectionWritingOp.serializableAsList().get(index);
  }

  @Override
  public WkSrlzStructSubcomponentFrameNode<XO, YO, ED> elements() {
    return this.elementComponent.body();
  }

}
