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
package weliyek.serialization.number;

import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;

public final class WkSimplifiedNumberSrlzInputPacketDecoderFrameLeafNodeCore<
                        X extends Number,
                        XO extends WkSerdeDTreeNumberReader<
                                      X,
                                      WkSettingsSrlzPacketOperationData,
                                      WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                                      WkResultSrlzPacketOperationData<X>,
                                      XD>,
                        XD extends WkSerdeDTreeNumberDefinition<X>>
    extends WkNumberSrlzInputPacketDecoderFrameLeafNodeCore<
                        X,
                        WkSettingsSrlzPacketOperationData,
                        WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                        WkDecodingRuntimeSrlzPacketOperationCtrl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>>,
                        WkResultSrlzPacketOperationData<X>,
                        XO,
                        WkSimplifiedNumberSrlzInputPacketDecoderFrameLeafNodeCore<X,XO,XD>,
                        XD,
                        WkSzInputBytestreamBase<?>,
                        WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<X,XO,?,XD>>
{

  public WkSimplifiedNumberSrlzInputPacketDecoderFrameLeafNodeCore(
    int index,
    WkSettingsSrlzPacketOperationData settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSrlzInputPacketFieldFrameNodeCore<X,?,XD,?,?,?> readingfieldCore,
    WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<X,XO,?,XD> definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, readingfieldCore, definitionCore, operationBody);
  }

  @Override
  protected void onSerializerFullReadingCompletion(X deserialized) {
    // Nothing to do.
  }

  @Override
  public long expectedBytes() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  protected WkSimplifiedNumberSrlzInputPacketDecoderFrameLeafNodeCore<X,XO,XD> getThis() {
    return this;
  }

  @Override
  protected void onDeserilizingOperationInitialization() {
    // Nothing to do.
  }

}
