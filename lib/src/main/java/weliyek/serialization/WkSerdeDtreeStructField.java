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

import java.util.function.Predicate;

import weliyek.serialization.filter.WkSrlzPacketNodePredicate;
import weliyek.serialization.tree.WkSerdeDtreeCtrl;
import weliyek.serialization.tree.WkSerdeDtreeStruct;

/**
 * The main node that defines the serialization rules for a given data type.
 * 
 * @param <D> Serialization characteristics and rules for the target data type.
 */
public interface WkSerdeDtreeStructField<
                        D extends WkSerdeDtreeStructDefinition<?>>
    extends WkSerdeDtreeStruct,
            WkSerdeDtreeCtrl
{

  String label();

  D definition();

  String name();

  WkSrlzPacketNodePredicate<?, ?> makeTester(Predicate<WkSerdeDtreeMsgField<?,?,? super D>> test, String description);

  boolean isRxRequired();

  boolean isRxOptional();

  boolean isTxRequired();

  boolean isTxOptional();

}
