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
package weliyek.amat.base;

import java.util.function.Predicate;

import weliyek.amat2.protocol.filter.FieldTester;

/**
 * The main segment that defines the serialization rules for a given data type. It has also a
 * shortcut that can create a {@link FieldTester} with a given {@link Predicate} for the 
 * represented data type.
 * 
 * @param <D> Serialization characteristics and rules for the target data type.
 */
public interface WkSzStructComponent<
                        D extends WkSzDefinition<?,?>>
    extends WkSzStructSegment,
            WkSzBinderSegment
{

  String label();

  D definition();

  String name();

  FieldTester<?, ?> makeTester(Predicate<WkSzPacketField<?,?,?>> test, String description);

}
