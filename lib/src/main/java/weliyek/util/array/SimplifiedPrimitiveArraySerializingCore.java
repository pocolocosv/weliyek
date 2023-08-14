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

import java.util.Objects;
import java.util.function.Consumer;

import weliyek.serialization.WkPrimitiveArraySrlzStructDefinitionFrameLeafNode;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkPrimitiveArraySrlzOutputPacketEncoderFrameLeafNode;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkEncodingResultSrlzPacketOperationData;

public class SimplifiedPrimitiveArraySerializingCore<
                        Y extends WkPrimitiveArrayBase<?,?>,
                        YS extends WkSettingsSrlzPacketOperationData,
                        D extends WkPrimitiveArraySrlzStructDefinitionFrameLeafNode<Y,?>,
                        YO extends WkPrimitiveArraySrlzOutputPacketEncoderFrameLeafNode<
                                        Y,
                                        YS,
                                        WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                                        WkEncodingResultSrlzPacketOperationData,
                                        D>>
    extends PrimitiveArraySerializerWritingCore<
                        Y,
                        YS,
                        WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkSequenceEncodingRuntimeSrlzPacketOperationCtrl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>>,
                        WkEncodingResultSrlzPacketOperationData,
                        YO,
                        SimplifiedPrimitiveArraySerializingCore<Y,YS,D,YO>,
                        D,
                        WkSzOutputBytestreamBase<?>,
                        SimplifiedPrimitiveArraySerializerCore<Y,?,?,YS,YO,D>>
{

  private final Consumer<? super SimplifiedPrimitiveArraySerializingCore<Y,YS,D,YO>>
                    onInitializing;

  public SimplifiedPrimitiveArraySerializingCore(
    int index,
    Y serializable,
    YS settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<Y,?,D,?,?,?> serializingfieldCore,
    SimplifiedPrimitiveArraySerializerCore<Y,?,?,YS,YO,D> definitionCore,
    YO operationBody,
    Consumer<? super SimplifiedPrimitiveArraySerializingCore<Y,YS,D,YO>> onInitializing) {
    super(index, serializable, settings, parentBytestream, serializingfieldCore, definitionCore, operationBody);
    this.onInitializing = Objects.requireNonNull(onInitializing);
  }

  @Override
  protected void onSerializingOperationInitialization() {
    this.onInitializing.accept(getThis());
  }

  @Override
  protected SimplifiedPrimitiveArraySerializingCore<Y, YS, D, YO> getThis() {
    return this;
  }

  @Override
  public long expectedBytes() {
    // TODO Auto-generated method stub
    return 0;
  }

}
