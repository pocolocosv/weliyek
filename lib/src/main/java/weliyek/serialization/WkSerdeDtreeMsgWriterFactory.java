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

@FunctionalInterface
public interface WkSerdeDtreeMsgWriterFactory<
                        T,
                        YS extends WkSerdeDtreeOperationSettings,
                        YDC extends WkSerdeDtreeStructDefinitionCore<? super T,?,?,?,?,?,?,?,?,? super YS,?,?,?,?,?,? super YOC,? super AYBC,?,?>,
                        YOC extends WkSerdeDtreeMsgWriterCore<? super T,? super YS,?,?,?,?,?,?,? super YDC,? super AYBC>,
                        AYBC extends WkSerdeDtreeBytestreamOutputBase<?>>
    extends WkSerdeDtreeMsgOperationFactory
{

  YOC newWritingCore(
    int index,
    T serializable,
    YS settings,
    AYBC parentBytestream,
    @SuppressWarnings("rawtypes") WkSerdeDtreeMsgOutputFieldCore dataFieldCore,
    YDC definitionCore);

}
