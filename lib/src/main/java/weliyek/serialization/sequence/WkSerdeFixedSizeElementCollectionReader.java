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
import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeSequenceCommon;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeMsgReader;
import weliyek.serialization.WkSerdeDtreeMsgInputField;
import weliyek.serialization.WkSerdeDtreeMsgInputFieldCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSerdeDtreeStructDefinition;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;

public final class WkSerdeFixedSizeElementCollectionReader<
                        T extends Collection<ET>,
                        XS extends WkSerdeDtreeOperationSettings,
                        ET,
                        EXS extends WkSerdeDtreeOperationSettings,
                        EXD extends WkSerdeDtreeStructDefinition<ET>,
                        EXO extends WkSerdeDtreeMsgReader<ET,EXS,?,?,EXD>>
    implements WkSerdeElementCollectionReader<
                        T,
                        XS,
                        WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<T>,
                        WkSerdeFixedSizeElementCollection<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                        ET, EXD, EXO>,
                WkSerdeDtreeFixedSizeSequenceReader<
                        T,
                        XS,
                        WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<T>,
                        WkSerdeFixedSizeElementCollection<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>>
{

  final WkSerdeElementCollectionReaderCoreSimplified<
                        T, XS,
                        WkSerdeFixedSizeElementCollection<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                        WkSerdeFixedSizeElementCollectionReader<T,XS,ET,EXS,EXD,EXO>,
                        ET, EXS, EXD, EXO> operationCore;

  WkSerdeFixedSizeElementCollectionReader(
    int index,
    XS settings,
    WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
    WkSerdeDtreeMsgInputFieldCore<
      T,?,WkSerdeFixedSizeElementCollection<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
      ?,?,?> deserializingfieldCore,
    WkSerdeElementCollectionDefinitionCoreSimplified<
      T,XS,WkSerdeFixedSizeElementCollection<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
      WkSerdeFixedSizeElementCollectionReader<T,XS,ET,EXS,EXD,EXO>,
      ?,?,?,ET,EXS,EXD,EXO,?,?,?,?,?> definitionCore) {
    this.operationCore = new WkSerdeElementCollectionReaderCoreSimplified<
                                T, XS,
                                WkSerdeFixedSizeElementCollection<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                                WkSerdeFixedSizeElementCollectionReader<T,XS,ET,EXS,EXD,EXO>,
                                ET, EXS, EXD, EXO>(
                                    index,
                                    settings,
                                    parentBytestream,
                                    deserializingfieldCore,
                                    definitionCore,
                                    this);
  }

  @Override
  public WkSrlzInputPacketSubfieldFrameNode<ET,EXD,EXO> elements() {
    return this.operationCore.elements();
  }

  @Override
  public WkSerdeFixedSizeElementCollection<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?> definition() {
    return this.operationCore.definition();
  }

  @Override
  public XS settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSerdeDtreeOperationResult<T>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSerdeDtreeMsgInputField<T, WkSerdeFixedSizeElementCollection<T,XS,?,ET,EXS,EXD,EXO,?,?,?,?>,?>
  packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public List<WkSrlzInputPacketSubfieldFrameNode<?,?,?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public String toString() {
    return this.name();
  }

}