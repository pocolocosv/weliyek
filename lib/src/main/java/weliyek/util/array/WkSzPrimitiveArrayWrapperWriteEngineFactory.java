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

import java.util.function.BiFunction;

import weliyek.serialization.WkSrlzEngineEncoder;
import weliyek.serialization.WkSzWriteEngineFactory;
import weliyek.serialization.WkPrimitiveArraySrlzOutputPacketEncoderFrameLeafNode;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationCtrl;

public class WkSzPrimitiveArrayWrapperWriteEngineFactory<
                        Y extends WkPrimitiveArrayBase<?,?>,
                        QC extends WkSequenceEncodingRuntimeSrlzPacketOperationCtrl<?,?,?>,
                        O extends WkPrimitiveArraySrlzOutputPacketEncoderFrameLeafNode<? extends Y,?,?,?,?>>
    extends WkSzWriteEngineFactory<Y, QC, O>
{

  public WkSzPrimitiveArrayWrapperWriteEngineFactory(
    String label,
    BiFunction<
      QC, O,
      WkSrlzEngineEncoder<Y,QC,O>> engineSupplier) {
    super(label, engineSupplier);
  }

}