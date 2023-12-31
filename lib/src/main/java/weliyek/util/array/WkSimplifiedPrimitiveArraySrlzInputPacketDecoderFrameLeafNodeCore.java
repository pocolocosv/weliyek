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

import weliyek.serialization.WkPrimitiveArraySrlzInputPacketDecoderFrameLeafNode;
import weliyek.serialization.WkPrimitiveArraySrlzStructDefinitionFrameLeafNode;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSequenceDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkSequenceDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;

public class WkSimplifiedPrimitiveArraySrlzInputPacketDecoderFrameLeafNodeCore<
                        X extends WkPrimitiveArray<?,?>,
                        XS extends WkSettingsSrlzPacketOperationData,
                        D extends WkPrimitiveArraySrlzStructDefinitionFrameLeafNode<X>,
                        XO extends WkPrimitiveArraySrlzInputPacketDecoderFrameLeafNode<
                                          X,
                                          XS,
                                          WkSequenceDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                                          WkResultSrlzPacketOperationData<X>,
                                          D>>
    extends WkPrimitiveArraySrlzInputPacketDecoderFrameLeafNodeCore<
                        X,
                        XS,
                        WkSequenceDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                        WkSequenceDecodingRuntimeSrlzPacketOperationCtrl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkSequenceDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>>,
                        WkResultSrlzPacketOperationData<X>,
                        XO,
                        WkSimplifiedPrimitiveArraySrlzInputPacketDecoderFrameLeafNodeCore<X,XS,D,XO>,
                        D,
                        WkSzInputBytestreamBase<?>,
                        WkSimplifiedPrimitiveArraySrlzStructDefinitionFrameLeafNodeCore<X,XS,XO,?,?,D>>
{

  private final Consumer<? super WkSimplifiedPrimitiveArraySrlzInputPacketDecoderFrameLeafNodeCore<X,XS,D,XO>>
                    onInitializing;

  public WkSimplifiedPrimitiveArraySrlzInputPacketDecoderFrameLeafNodeCore(
    int index,
    XS settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSrlzInputPacketFieldFrameNodeCore<X,?,D,?,?,?> deserializingfieldCore,
    WkSimplifiedPrimitiveArraySrlzStructDefinitionFrameLeafNodeCore<X,XS,XO,?,?,D> definitionCore,
    XO operationBody,
    Consumer<? super WkSimplifiedPrimitiveArraySrlzInputPacketDecoderFrameLeafNodeCore<X,XS,D,XO>> onInitializing) {
    super(index, settings, parentBytestream, deserializingfieldCore, definitionCore, operationBody);
    this.onInitializing = Objects.requireNonNull(onInitializing);
  }

  @Override
  protected void onDeserilizingOperationInitialization() {
    this.onInitializing.accept(getThis());
  }

  @Override
  protected void onSerializerFullReadingCompletion(X deserialized) {
    // Nothing to do.
  }

  @Override
  protected WkSimplifiedPrimitiveArraySrlzInputPacketDecoderFrameLeafNodeCore<X,XS,D,XO> getThis() {
    return this;
  }

  @Override
  public long expectedBytes() {
    // TODO Auto-generated method stub
    return 0;
  }

}
