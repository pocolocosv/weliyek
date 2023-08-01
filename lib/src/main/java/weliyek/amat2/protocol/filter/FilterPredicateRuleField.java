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
package weliyek.amat2.protocol.filter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import weliyek.amat.base.WkSzDefinition;

public class FilterPredicateRuleField
        extends FilterPredicateRuleBase
{

    private final FieldTester<?,?> tester;
    private final List<WkSzDefinition<?,?>> matchTargets;
    private final List<FieldTester<?,?>> testers;
    final ExclusiveOption option;

    public FilterPredicateRuleField(
      FieldTester<?,?> tester,
      ExclusiveOption opt) {
        super(tester.toString());
        this.tester = Objects.requireNonNull(tester);
        this.option = Objects.requireNonNull(opt);

        this.matchTargets = Collections.singletonList(tester.targetProtocolField());
        this.testers = Collections.singletonList(tester);
    }

    @Override
    public List<WkSzDefinition<?,?>> matchTargets() {
        return this.matchTargets;
    }

    @Override
    List<FieldTester<?,?>> testers() {
        return testers;
    }

    FieldTester<?,?> onlyTester() {
        return this.tester;
    }

    @Override
    public Set<? extends FilterPredicateRuleBase> subrules() {
        return Collections.emptySet();
    }

    @Override
    FilterPredicateRuleBaseResult newResult() {
        return new FilterPredicateRuleFieldResult(this);
    }

}
