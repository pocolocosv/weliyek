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

import weliyek.amat.base.ComponentSegmentCore;
import weliyek.amat.base.DefinitionSegment;
import weliyek.amat.base.DefinitionSegmentCore;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.ProtocolDefinitionFactory;
import weliyek.amat.base.SubcomponentSegment;
import weliyek.amat.base.input.DeserializingOperation;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.SerializingOperation;

public class SubcomponentSegmentCore<
                        T,
                        XS extends OperationSettings,
                        XD extends DefinitionSegment<T,?>,
                        XO extends DeserializingOperation<T,XS,?,?,XD>,
                        AXBC extends InputBytestreamGeneralBase<?>,
                        YS extends OperationSettings,
                        YD extends DefinitionSegment<T,?>,
                        YO extends SerializingOperation<T,YS,?,?,YD>,
                        AYBC extends OutputBytestreamGeneralBase<?>,
                        D extends DefinitionSegment<T,?>>
    extends ComponentSegmentCore<T, XS, XD, XO, AXBC, YS, YD, YO, AYBC, D>
    implements SubcomponentSegment<D>
{

  private final DefinitionSegmentCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> parentDefinitionCore;

  SubcomponentSegmentCore(
    String label,
    ProtocolDefinitionFactory<T,XS,XD,XO,AXBC,YS,YD,YO,AYBC,D> definitionFactory,
    DefinitionSegmentCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> parentDefinitionCore) {
    super(label, definitionFactory);
    this.parentDefinitionCore = Objects.requireNonNull(parentDefinitionCore);
  }

  @Override
  protected DefinitionSegmentCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> parentDefinitionCore() {
    return this.parentDefinitionCore;
  }

}
