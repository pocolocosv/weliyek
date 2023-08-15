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
package weliyek.bitcoin;

import java.io.InputStream;

import weliyek.ketza.base.composite.CoreAccessor;
import weliyek.ketza.base.composite.lineage.LineageChildCompositeCommissionerKernelWithAncestor;
import weliyek.ketza.base.composite.serializable.SerializableFromInputStreamCommissionerKernel;
import weliyek.ketza.bitstreamable.scion.duo.BitstreamDuoScionCommissioner;

public class BitcoinMsgStreamCommissioner
        implements MessageBaseCommissioner<BitcoinMsgRWBody,
                                           BitcoinMsgStream,
                                           BitcoinMsgStreamBody>,
                   BitstreamDuoScionCommissioner<BitcoinMsgRWBody,
                                                 BitcoinMsgStream,
                                                 BitcoinMsgStreamBody,
                                                 BitcoinProtocolMessageConfig>,
                   CoreAccessor<BitcoinMsgStream,
                                BitcoinMsgStreamBody>
{

    private final Factory<BitcoinMsgStreamBody> bitstreamFactory;

    private final LineageChildCompositeCommissionerKernelWithAncestor<BitcoinMsgRWBody, BitcoinMsgStreamBody> lineageKernel = new LineageChildCompositeCommissionerKernelWithAncestor<>();
    private final SerializableFromInputStreamCommissionerKernel<BitcoinMsgStream, BitcoinMsgStreamBody, BitcoinProtocolMessageConfig> deserializingKernel = new SerializableFromInputStreamCommissionerKernel<>();

    BitcoinMsgStreamCommissioner(Factory<BitcoinMsgStreamBody> bitstreamFactory,
                                 int minPoolSize,
                                 int maxPoolSize)
    {
        this.bitstreamFactory = bitstreamFactory;
    }

    @Override
    public BitcoinMsgStreamBody extractCoreFromBody(BitcoinMsgStream bitstream) {
        if (bitstream instanceof BitcoinMsgStreamBody) {
            return (BitcoinMsgStreamBody)bitstream;
        }
        throw new ClassCastException("Could NOT cast "
                + bitstream.getClass().getSimpleName()
                + " object into "
                + BitcoinMsgStreamBody.class.getSimpleName());
    }

    @Override
    public BitcoinMsgStreamBody commissionWithAncestor(BitcoinMsgRWBody ancestorCore) {
        final BitcoinMsgStreamBody bitstreamCore = bitstreamFactory.build();
        lineageKernel.commissionWithAncestor(bitstreamCore, ancestorCore);
        return bitstreamCore;
    }

    @Override
    public BitcoinMsgStreamBody commissionWithInputStream(BitcoinProtocolMessageConfig config, InputStream in) {
        final BitcoinMsgStreamBody bitstreamCore = bitstreamFactory.build();
        deserializingKernel.commissionWithInputStream(bitstreamCore, config, in);
        return bitstreamCore;
    }

    /*
    @Override
    public BitcoinMsgStreamBody commissionableCore(final BitcoinMsgStreamBody stream) {
        return stream;
    }

    @Override
    protected boolean isInstanceOfSealableBody(final BitcoinMsgStream stream) {
        return stream instanceof BitcoinMsgStreamBody;
    }

    @Override
    protected BitcoinMsgStreamBody castPublicInterfaceToBody(final BitcoinMsgStream stream) {
        return (BitcoinMsgStreamBody)stream;
    }

    @Override
    protected BitcoinMsgStreamBody newUninitializedCommissionable() {
        return new BitcoinMsgStreamBody(commissioners);
    }
    */

}
