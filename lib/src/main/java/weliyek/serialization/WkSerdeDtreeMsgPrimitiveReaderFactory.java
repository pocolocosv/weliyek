/*
 * Copyright (C) 2024  Ricardo Villalobos - All Rights Reserved
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

@FunctionalInterface
public interface WkSerdeDtreeMsgPrimitiveReaderFactory<
                        XS extends WkSerdeDtreeOperationSettings,
                        XDC extends WkSerdeDtreePrimitiveStructDefinitionCore<?,? super XS,?,?,?,?,?,? super XOC,? super AXBC,?,?,?,?,?,?,?,?,?,?>,
                        XOC extends WkSerdeDtreePrimitiveMsgReaderCore<
                                      ?,? super XS,?,?,?,?,?,?,? super XDC,? super AXBC>,
                        AXBC extends WkSerdeDtreeBytestreamInputBase<?>>
    extends WkSerdeDtreeMsgReaderFactory<XS, XDC, XOC, AXBC>
{

}
