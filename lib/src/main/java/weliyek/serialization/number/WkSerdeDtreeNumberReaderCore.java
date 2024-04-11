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

import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataReaderDecoderCore;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponentCore;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;

public abstract class WkSerdeDtreeNumberReaderCore<
                        X extends Number,
                        XS extends WkSerdeDtreeOperationSettings,
                        XQ extends WkSerdeDtreeOperationInputRuntime<?>,
                        XQC extends WkSerdeDtreeOperationInputRuntimeCtrl<?,?,XQ>,
                        XR extends WkSerdeDtreeOperationResult<X>,
                        XO extends WkSerdeDtreeNumberReader<X,XS,XQ,XR,XD>,
                        XOC extends WkSerdeDtreeNumberReaderCore<X,XS,XQ,XQC,XR,XO,?,XD,AXB,DC>,
                        XD extends WkSerdeDtreeNumberDefinition<X>,
                        AXB extends WkSerdeDtreeBytestreamInputBase<?>,
                        DC extends WkSerdeDtreeNumberDefinitionCore<X,XS,XQC,XR,XD,XO,AXB,?,?,?,?,?,?,? extends XD,DC>>
    extends WkSerdeDtreeNodeDataReaderDecoderCore<X, XS, XQ, XQC, XR, XO, XOC, XD, AXB, DC>
    implements WkSerdeDtreeNumberReader<X, XS, XQ, XR, XD>
{

  protected WkSerdeDtreeNumberReaderCore(
    int index,
    XS settings,
    AXB parentBytestream,
    WkSerdeDtreeNodeDataInputComponentCore<X, ?, XD, ?, ?, ?> packetField,
    DC definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, packetField, definitionCore, operationBody);
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

}
