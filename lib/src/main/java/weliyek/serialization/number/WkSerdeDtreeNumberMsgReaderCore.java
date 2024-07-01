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

import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeMsgInputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreePrimitiveMsgReaderCore;

public abstract class WkSerdeDtreeNumberMsgReaderCore<
                        X extends Number,
                        XS extends WkSerdeDtreeOperationSettings,
                        XQ extends WkSerdeDtreeOperationInputRuntime<?>,
                        XQC extends WkSerdeDtreeOperationInputRuntimeCtrl<?,?,XQ>,
                        XR extends WkSerdeDtreeOperationResult<X>,
                        XO extends WkSerdeDtreeNumberMsgReader<X,XS,XQ,XR,XD>,
                        XOC extends WkSerdeDtreeNumberMsgReaderCore<X,XS,XQ,XQC,XR,XO,?,XD,XDC,AXB>,
                        XD extends WkSerdeDtreeNumberStructDefinition<X>,
                        XDC extends WkSerdeDtreeNumberDefinitionCore<X,XS,XQC,XR,XD,?,XO,XOC,AXB,?,?,?,?,?,?,?,?,? extends XD,?>,
                        AXB extends WkSerdeDtreeBytestreamInputBase<?>>
    extends WkSerdeDtreePrimitiveMsgReaderCore<
                        X, XS, XQ, XQC, XR, XO, XOC, XD, XDC, AXB>
    implements WkSerdeDtreeNumberMsgReader<X, XS, XQ, XR, XD>
{

  protected WkSerdeDtreeNumberMsgReaderCore(
    int index,
    WkSerdeDtreeMsgInputFieldCore<?,XS,?,?,AXB,?,?,?> readerFieldCore,
    XDC definitionCore,
    XO operationBody) {
    super(index, readerFieldCore, definitionCore, operationBody);
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
