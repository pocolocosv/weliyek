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
package weliyek.serialization;

import java.util.Objects;
import java.util.function.Consumer;

public class WkSimplifiedAggregatorSrlzOutputPacketEncoderFrameNodeCore<
                        T,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YD extends WkAggregatorSrlzStructDefinitionFrameNode<T>,
                        YO extends WkAggregatorSrlzOutputPacketEncoderFrameNode<
                                        T,YS,
                                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                                        WkResultSrlzPacketOperationData<T>,
                                        YD>>
    extends WkAggregatorSrlzOutputPacketEncoderFrameNodeCore<
                        T,
                        YS,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkEncodingRuntimeSrlzPacketOperationCtrl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>>,
                        WkResultSrlzPacketOperationData<T>,
                        YD,
                        YO,
                        WkSimplifiedAggregatorSrlzOutputPacketEncoderFrameNodeCore<T,YS,YD,YO>,
                        WkSzOutputBytestreamBase<?>,
                        WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore<T,?,?,?,YS,YD,YO,?>>
{

  private Consumer<? super WkSimplifiedAggregatorSrlzOutputPacketEncoderFrameNodeCore<T,YS,YD,YO>> onInitializing;

  public WkSimplifiedAggregatorSrlzOutputPacketEncoderFrameNodeCore(
    int index,
    T serializable,
    YS settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<T,?,YD,?,?,?> serializingfieldCore,
    WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore<T,?,?,?,YS,YD,YO,?> definitionCore,
    YO body,
    Consumer<? super WkSimplifiedAggregatorSrlzOutputPacketEncoderFrameNodeCore<T,YS,YD,YO>> onInitializing) {
    super(index, serializable, settings, parentBytestream, serializingfieldCore, definitionCore, body);
    this.onInitializing = Objects.requireNonNull(onInitializing);
  }

  @Override
  protected void onAggregatorInitialization() {
    this.onInitializing.accept(getThis());
  }

  @Override
  protected WkSimplifiedAggregatorSrlzOutputPacketEncoderFrameNodeCore<T,YS,YD,YO> getThis() {
    return this;
  }

}
