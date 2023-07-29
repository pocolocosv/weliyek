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
package weliyek.amat.basic.number;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.input.DeserializingFieldCore;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.ReadingRuntimeControl;
import weliyek.amat.basic.serializer.SerializerReadingCore;

public abstract class NumberReadingCore<
                        X extends Number,
                        XS extends OperationSettings,
                        XQ extends DeserializingRuntime<?>,
                        XQC extends ReadingRuntimeControl<?,?,XQ>,
                        XR extends DeserializingResult<X>,
                        XO extends NumberDeserializing<X,XS,XQ,XR,XD>,
                        XOC extends NumberReadingCore<X,XS,XQ,XQC,XR,XO,?,XD,AXB,DC>,
                        XD extends NumberDefinition<X,XO>,
                        AXB extends InputBytestreamGeneralBase<?>,
                        DC extends NumberSerializerCore<X,XS,XQC,XR,XD,XO,AXB,?,?,?,?,?,?,? extends XD,DC>>
    extends SerializerReadingCore<X, XS, XQ, XQC, XR, XO, XOC, XD, AXB, DC>
    implements NumberDeserializing<X, XS, XQ, XR, XD>
{

  protected NumberReadingCore(
    int index,
    XS settings,
    AXB parentBytestream,
    DeserializingFieldCore<X, ?, XD, ?, ?, ?> packetField,
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
