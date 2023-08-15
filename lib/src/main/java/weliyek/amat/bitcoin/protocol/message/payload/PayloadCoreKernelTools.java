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

import java.io.InputStream;

import weliyek.amat.bitcoin.protocol.BitcoinProtocolMessageConfig;
import weliyek.amat.bitcoin.protocol.message.BitcoinMsgRWBody;
import weliyek.amat.bitcoin.protocol.message.MessagePayloadHandlerRO;
import weliyek.amat.bitcoin.protocol.message.MessageRWArgs;
import weliyek.amat.bitcoin.protocol.message.MessageUnpackedCore;

public interface PayloadCoreKernelTools<IK extends PayloadCoreKernelRO<?>,
                                        VK extends PayloadCoreKernelRW<?>>
{

    PayloadCoreKernelRO<? extends PayloadCoreKernelRO<?>> commissionPayloadKernelRO(MessageUnpackedCore<?, ?, ?> owner,
                                                                                    InputStream                  in,
                                                                                    BitcoinProtocolMessageConfig config);

    PayloadCoreKernelRW<? extends PayloadCoreKernelRW<?>> commissionPayloadKernelWithArgs(BitcoinMsgRWBody owner,
                                                                                          MessageRWArgs args);

    PayloadCoreKernelRW<? extends PayloadCoreKernelRW<?>> commissionPayloadKernelWithRO(BitcoinMsgRWBody owner,
                                                                                        MessagePayloadHandlerRO roHandler);

}
