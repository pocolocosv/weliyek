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
package weliyek.amat.basic.aggregator;

import java.util.Objects;

import weliyek.amat.base.WkSzStructComponentCoreBase;
import weliyek.amat.base.WkSzDefinition;
import weliyek.amat.base.WkSzDefinitionCore;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.ProtocolDefinitionFactory;
import weliyek.amat.base.SubcomponentSegment;
import weliyek.amat.base.input.WkSzPacketReaderOperation;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.WkSzPacketWriterOperation;

/**
 * Defines a struct component that has other components as a parent. Only the sibling 
 * type {@link WkSzStruct} does not have a parent.
 * 
 * @param <T>
 * @param <XS>
 * @param <XD>
 * @param <XO>
 * @param <AXBC>
 * @param <YS>
 * @param <YD>
 * @param <YO>
 * @param <AYBC>
 * @param <D>
 */
public class WkSzStructChildComponent<
                        T,
                        XS extends OperationSettings,
                        XD extends WkSzDefinition<T,?>,
                        XO extends WkSzPacketReaderOperation<T,XS,?,?,XD>,
                        AXBC extends InputBytestreamGeneralBase<?>,
                        YS extends OperationSettings,
                        YD extends WkSzDefinition<T,?>,
                        YO extends WkSzPacketWriterOperation<T,YS,?,?,YD>,
                        AYBC extends OutputBytestreamGeneralBase<?>,
                        D extends WkSzDefinition<T,?>>
    extends WkSzStructComponentCoreBase<T, XS, XD, XO, AXBC, YS, YD, YO, AYBC, D>
{

  private final WkSzDefinitionCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> parentDefinitionCore;

  WkSzStructChildComponent(
    String label,
    ProtocolDefinitionFactory<T,XS,XD,XO,AXBC,YS,YD,YO,AYBC,D> definitionFactory,
    WkSzDefinitionCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> parentDefinitionCore) {
    super(label, definitionFactory);
    this.parentDefinitionCore = Objects.requireNonNull(parentDefinitionCore);
  }

  @Override
  protected WkSzDefinitionCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> parentDefinitionCore() {
    return this.parentDefinitionCore;
  }

}
