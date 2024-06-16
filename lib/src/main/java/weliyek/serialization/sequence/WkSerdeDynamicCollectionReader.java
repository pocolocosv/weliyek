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

import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeMsgInputField;
import weliyek.serialization.WkSerdeDtreeMsgInputFieldCore;
import weliyek.serialization.WkSerdeDtreeMsgReader;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.WkSerdeDtreeStructDefinition;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgReader;
import weliyek.serialization.number.WkSerdeDtreeNumberStructDefinition;

public class WkSerdeDynamicCollectionReader<
                        T extends Collection<ET>,
                        XS extends WkSerdeDtreeOperationSettings,
                        ZT extends Number,
                        ZXS extends WkSerdeDtreeOperationSettings,
                        ZXO extends WkSerdeDtreeNumberMsgReader<ZT,ZXS,?,?,ZXD>,
                        ZXD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        ET,
                        EXS extends WkSerdeDtreeOperationSettings,
                        EXD extends WkSerdeDtreeStructDefinition<ET>,
                        EXO extends WkSerdeDtreeMsgReader<ET,EXS,?,?,EXD>,
                        VXS extends WkSerdeDtreeOperationSettingsVariableLength>
    implements WkSerdeDtreeDynamicCollectionReader<
                        T, XS,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<T>,
                        WkSerdeDynamicCollection<
                          T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>,
                        ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>
{

  final WkSerdeDtreeDynamicCollectionReaderCore<
                        T, XS,
                        WkSerdeDtreeDynamicCollectionReader<
                          T, XS,
                          WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                          WkSerdeDtreeOperationResult<T>,
                          WkSerdeDynamicCollection<
                            T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>,
                          ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>,
                        WkSerdeDynamicCollection<
                          T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>,
                        ZT, ZXS, ZXO, ZXD, ET, EXS, EXD, EXO, VXS> operationCore;

  WkSerdeDynamicCollectionReader(
    int index,
    XS settings,
    WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
    WkSerdeDtreeMsgInputFieldCore<?,?,?,?,?,?,?,?> readerFieldCore,
    WkSerdeDtreeDynamicCollectionDefinitionCore<
      T, XS,
      WkSerdeDtreeDynamicCollectionReader<
        T, XS,
        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
        WkSerdeDtreeOperationResult<T>,
        WkSerdeDynamicCollection<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ? extends ZXD, ET, EXS, EXD, EXO, ?, ?, ?, ? extends EXD, VXS, ?>,
        ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>,
      WkSerdeDynamicCollection<
        T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>,
      ?,?,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?,
      ? extends WkSerdeDynamicCollection<
                  T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>>
      definitionCore) {
    this.operationCore = new WkSerdeDtreeDynamicCollectionReaderCore<
                                T, XS,
                                WkSerdeDtreeDynamicCollectionReader<
                                  T, XS,
                                  WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                                  WkSerdeDtreeOperationResult<T>,
                                  WkSerdeDynamicCollection<
                                    T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>,
                                  ZT, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>,
                                WkSerdeDynamicCollection<
                                  T,XS,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>,
                                ZT, ZXS, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>(
                                    index,
                                    settings,
                                    parentBytestream,
                                    readerFieldCore,
                                    definitionCore,
                                    this);
  }

  @Override
  public Optional<WkSerdeDtreeMsgInputField<ZT, ZXD, ZXO>> size() {
    return this.operationCore.size();
  }

  @Override
  public
  Optional<WkSerdeDtreeMsgInputField<T, WkSerdeVariableSizeElementCollection<T, VXS, ?, ET, EXS, EXD, EXO, ?, ?, ?, ?>, WkSerdeVariableSizeElementCollectionReader<T, VXS, ET, EXS, EXD, EXO>>>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

  @Override
  public
  WkSerdeDynamicCollection<T, XS, ?, ZT, ZXS, ZXO, ZXD, ?, ?, ?, ? extends ZXD, ET, EXS, EXD, EXO, ?, ?, ?, ? extends EXD, VXS, ?>
  definition() {
    return this.operationCore.definition();
  }

  @Override
  public XS settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput> dashboard() {
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
  public
  WkSerdeDtreeMsgInputField<?,?,?>
  parentField() {
    return this.operationCore.parentField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public List<WkSerdeDtreeMsgInputField<?,?,?>> subfields() {
    return this.operationCore.subfields();
  }

}
