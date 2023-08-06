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
package weliyek.amat.base.output;

import java.io.IOException;

import weliyek.amat.base.CommonOperationRuntimeControl;
import weliyek.serialization.bytestream.OutputBytestream;
import weliyek.serialization.bytestream.OutputBytestreamGeneralBase;

public interface WritingRuntimeControl<
                        B extends OutputBytestream,
                        BC extends OutputBytestreamGeneralBase<? extends B>,
                        Q extends SerializingRuntime<B>>
    extends SerializingRuntime<B>,
            CommonOperationRuntimeControl<B, BC, Q>
{

  void writeByte(int b) throws IOException;

}
