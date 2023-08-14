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
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

import weliyek.serialization.WkOperationSettingsFactory;
import weliyek.serialization.WkSzPacketReaderOperationCoreFactory;
import weliyek.serialization.WkSzPacketWriterOperationCoreFactory;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSzBasicReadingResult;
import weliyek.serialization.WkSzBasicSequenceReadingRuntime;
import weliyek.serialization.WkSzBasicWritingResult;
import weliyek.serialization.WkSrlzStructDefinitionFrameNode;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzInputPacketDecoderFrameNode;
import weliyek.serialization.WkSrlzOutputPacketEncoderFrameNode;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzSequenceReadingRuntime;
import weliyek.serialization.WkSzSequenceReadingRuntimeControl;
import weliyek.serialization.WkSzSequenceWritingRuntime;
import weliyek.serialization.WkSzSequenceWritingRuntimeControl;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSzWritingResult;

public class SimplifiedCollectionDefinitionCore<
                        T extends Collection<ET>,
                        XS extends WkSzOperationSettings,
                        XD extends WkSzCollectionAndElementsDefinition<T,XO,?,ET,?>,
                        XO extends CollectionAndElementsFieldDeserializer<
                                        T,
                                        XS,
                                        WkSzSequenceReadingRuntime<WkSzInputBytestream>,
                                        WkSzReadingResult<T>,
                                        XD,
                                        ET,EXD,EXO>,
                        YS extends WkSzOperationSettings,
                        YD extends WkSzCollectionAndElementsDefinition<T,?,YO,ET,?>,
                        YO extends CollectionAndElementsFieldSerializer<
                                        T,
                                        YS,
                                        WkSzSequenceWritingRuntime<WkSzOutputBytestream>,
                                        WkSzWritingResult,
                                        YD,
                                        ET,EYD,EYO>,
                        ET,
                        EXS extends WkSzOperationSettings,
                        EXD extends WkSrlzStructDefinitionFrameNode<ET,?>,
                        EXO extends WkSrlzInputPacketDecoderFrameNode<ET,EXS,?,?,EXD>,
                        EYS extends WkSzOperationSettings,
                        EYD extends WkSrlzStructDefinitionFrameNode<ET,?>,
                        EYO extends WkSrlzOutputPacketEncoderFrameNode<ET,EYS,?,?,EYD>,
                        ED extends WkSrlzStructDefinitionFrameNode<ET,?>,
                        D extends WkSzCollectionAndElementsDefinition<T,XO,YO,ET,ED>>
    extends CollectionAndElementsFieldDefinitionCore<
                        T,
                        XS,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        WkSzSequenceReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkSzSequenceReadingRuntime<WkSzInputBytestream>>,
                        WkSzReadingResult<T>,
                        XD,
                        XO,
                        WkSzInputBytestreamBase<?>,
                        YS,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkSzSequenceWritingRuntimeControl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkSzSequenceWritingRuntime<WkSzOutputBytestream>>,
                        WkSzWritingResult,
                        YD,
                        YO,
                        WkSzOutputBytestreamBase<?>,
                        ET,
                        EXS,
                        EXD,
                        EXO,
                        EYS,
                        EYD,
                        EYO,
                        ED,
                        D,
                        SimplifiedCollectionDefinitionCore<
                          T,XS,XD,XO,YS,YD,YO,
                          ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,D>>
{

  protected SimplifiedCollectionDefinitionCore(
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    WkSzPacketReaderOperationCoreFactory<
      T,XS,XD,SimplifiedCollectionDefinitionCore<T,XS,XD,XO,YS,YD,YO,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,D>,
      XO,WkSzInputBytestreamBase<?>> readingOpFactory,
    WkSzPacketWriterOperationCoreFactory<
      T,YS,YD,SimplifiedCollectionDefinitionCore<T,XS,XD,XO,YS,YD,YO,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,D>,
      YO,WkSzOutputBytestreamBase<?>> writingOpFactory,
    String elementsLabel,
    ToIntFunction<? super XO> elementsDeserializingNumOfOps,
    WkOperationSettingsFactory<XO, EXS> elementsRxSettingsFactory,
    WkOperationSettingsFactory<YO, EYS> elementsTxSettingsFactory,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ET,EXS,EXD,EXO,WkSzInputBytestreamBase<? extends WkSzInputBytestream>,EYS,EYD,EYO,
      WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,ED> elementsDefinitionFactory,
    Function<List<ET>, T> collectionFactory,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          componentCore,
          WkSzBasicSequenceReadingRuntime::new,
          WkSzBasicReadingResult::new,
          readingOpFactory,
          WkSzBasicSequenceWritingRuntime::new,
          WkSzBasicWritingResult::empty,
          writingOpFactory,
          elementsLabel,
          elementsDeserializingNumOfOps,
          elementsRxSettingsFactory,
          elementsTxSettingsFactory,
          (k,yo,i) -> yo.serializableAsList().get(i),
          elementsDefinitionFactory,
          (xo) -> collectionFactory.apply(xo.element().field().get().collectAllOperationValues()),
          definitionBody,
          serializableClass);
  }

  @Override
  protected SimplifiedCollectionDefinitionCore<T,XS,XD,XO,YS,YD,YO,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED,D> getThis() {
    return this;
  }

}
