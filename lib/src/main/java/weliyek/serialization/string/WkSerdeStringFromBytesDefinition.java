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
package weliyek.serialization.string;

import java.nio.charset.Charset;

import weliyek.serialization.WkSerdeDtreeStructSubfield;
import weliyek.util.array.WkSerdeDtreeByteArrayDefinition;

public interface WkSerdeStringFromBytesDefinition<
                        XO extends WkSerdeStringFromBytesReader<?,?,?,?,?,?>,
                        YO extends WkSerdeStringFromBytesWriter<?,?,?,?,?,?>,
                        SD extends WkSerdeDtreeByteArrayDefinition>
    extends WkSerdeStringFromPrimitiveArrayDefinition<XO, YO, SD>,
            WkSerdeStringFromBytes<WkSerdeDtreeStructSubfield<XO, YO, SD>>
{

  @Override
  Charset charset();

}
