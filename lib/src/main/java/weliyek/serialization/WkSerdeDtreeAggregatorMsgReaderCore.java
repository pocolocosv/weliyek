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

import java.util.List;
import java.util.Optional;

public abstract class WkSerdeDtreeAggregatorMsgReaderCore<
                        T,
                        XS extends WkSerdeDtreeOperationSettings,
                        XB extends WkSerdeDtreeBytestreamInput,
                        XBC extends WkSerdeDtreeBytestreamInputBase<? extends XB>,
                        XQ extends WkSerdeDtreeOperationInputRuntime<XB>,
                        XQC extends WkSerdeDtreeOperationInputRuntimeCtrl<XB,XBC,XQ>,
                        XR extends WkSerdeDtreeOperationResult<T>,
                        XD extends WkSerdeDtreeAggregatorStructDefinition<T>,
                        XDC extends WkSerdeDtreeAggregatorStructDefinitionCore<
                                      T,XS,XB,XBC,XQC,XR,XD,?,XO,XOC,AXB,?,?,?,?,?,?,?,?,?,?,? extends XD,?>,
                        XO extends WkSerdeDtreeAggregatorMsgReader<T,XS,XQ,XR,XD>,
                        XOC extends WkSerdeDtreeAggregatorMsgReaderCore<
                                      T,XS,XB,XBC,XQ,XQC,XR,XD,XDC,XO,?,AXB>,
                        AXB extends WkSerdeDtreeBytestreamInputBase<?>>
    extends WkSerdeDtreeMsgReaderCore<
                        T, XS, XQ, XQC, XR, XO, XOC, XD, XDC, AXB>
    implements WkSerdeDtreeAggregatorMsgReader<T, XS, XQ, XR, XD>
{

  protected WkSerdeDtreeAggregatorMsgSubfieldsReaders<T,XBC,XO> readinHandlerList;

  protected WkSerdeDtreeAggregatorMsgReaderCore(
    int index,
    XS settings,
    AXB parentBytestream,
    WkSerdeDtreeMsgInputFieldCore<?,?,?,?,?,?,?,?> msgFieldCore,
    XDC definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, msgFieldCore, definitionCore, operationBody);
    this.readinHandlerList = new WkSerdeDtreeAggregatorMsgSubfieldsReaders<>(this);
  }

  @SuppressWarnings("unchecked")
  public <ST,
          SXS extends WkSerdeDtreeOperationSettings,
          SXD extends WkSerdeDtreeStructDefinition<ST>,
          SXO extends WkSerdeDtreeMsgReader<ST,SXS,?,?,SXD>>
  WkSerdeDtreeMsgInputSubfieldCore<ST,SXS,SXD,SXO,T,XBC,XO> getSubfieldpacketFor(
    WkSerdeDtreeStructSubfieldCore<ST,T,SXS,SXD,SXO,XBC,XO,?,?,?,?,?,? extends SXD> protocolSubfieldCore) {
    return (WkSerdeDtreeMsgInputSubfieldCore<ST,SXS,SXD,SXO,T,XBC,XO>) this.readinHandlerList.getMsgSubfield(protocolSubfieldCore);
  }

  @Override
  public List<WkSerdeDtreeMsgInputField<?,?,?>> subfields() {
    return this.readinHandlerList.asSubfieldList();
  }

  @Override
  protected void onStartReading() {
    onAggregatorReadingInitialization();
  }

  protected abstract void onAggregatorReadingInitialization();

  @Override
  protected Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> onProcessingBytestream() {
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedSubfield = this.readinHandlerList.processBytestream();
    if (this.readinHandlerList.isCompleted()) {
        completeOperation();
    }
    return completedSubfield;
  }

  @Override
  public long expectedBytes() {
    // TODO Auto-generated method stub
    return 0;
  }

}
