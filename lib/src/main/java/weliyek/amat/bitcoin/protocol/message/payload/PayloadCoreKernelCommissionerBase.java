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
package weliyek.amat.bitcoin.protocol.message.payload;

import weliyek.ketza.base.composite.CoreAccessor;
import weliyek.ketza.base.composite.commissionable.AbstractCommissionableCommissioner;

public class PayloadCoreKernelCommissionerBase
                        <K extends   PayloadCoreKernelAbstract<K>
                                   & PayloadCoreKernel<K>>
        extends AbstractCommissionableCommissioner<K, K>
        implements PayloadCoreKernelCommissioner<K>,
                   CoreAccessor<K, K>
{

    private final PayloadFactory<K> factory;

    public PayloadCoreKernelCommissionerBase(PayloadFactory<K> factory) {
        this.factory = factory;
    }

    @Override
    public K extractCoreFromBody(K kernel) {
        return kernel;
    }

    @Override
    public CoreAccessor<K, K> accessor() {
        return this;
    }

    @Override
    protected Factory<K> factory() {
        return factory;
    }

}
