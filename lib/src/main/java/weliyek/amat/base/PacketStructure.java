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

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.function.Function;

import weliyek.amat.base.input.WkSzPacketReaderOperation;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.WkSzInputPacket;
import weliyek.amat.base.input.WkSzInputPacketCore;
import weliyek.amat.base.input.WkSzInputPacketCore.ReadingPacketParameters;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.WkSzOutputPacket;
import weliyek.amat.base.output.WkSzOutputPacketCore;
import weliyek.amat.base.output.WkSzOutputPacketCore.WritingParameters;
import weliyek.amat.base.output.WkSzPacketWriterOperation;
import weliyek.amat2.protocol.filter.Filter;

public final class PacketStructure<
                        T,
                        XS extends OperationSettings,
                        XD extends WkSzDefinition<T,?>,
                        XO extends WkSzPacketReaderOperation<T,XS,?,?,XD>,
                        AXBC extends InputBytestreamGeneralBase<?>,
                        YS extends OperationSettings,
                        YD extends WkSzDefinition<T,?>,
                        YO extends WkSzPacketWriterOperation<T,YS,?,?,YD>,
                        AYBC extends OutputBytestreamGeneralBase<?>,
                        D extends WkSzDefinition<T,XO>>
    extends WkSzStructComponentCore<
                        T, XS, XD, XO, AXBC,
                        YS, YD, YO, AYBC,
                        D>
{

  private final Function<InputStream, AXBC> inputbytestreamFactory;
  private final Function<OutputStream, AYBC> outputbytestreamFactory;

  public PacketStructure(
    String label,
    ProtocolDefinitionFactory<T,XS,XD,XO,AXBC,YS,YD,YO,AYBC,D> definitionFactory,
    Function<InputStream, AXBC> inputbytestreamFactory,
    Function<OutputStream, AYBC> outputbytestreamFactory) {
    super(label, definitionFactory);
    this.inputbytestreamFactory = Objects.requireNonNull(inputbytestreamFactory);
    this.outputbytestreamFactory = Objects.requireNonNull(outputbytestreamFactory);
  }

  public WkSzInputPacket<T, XD, XO>
  newInputPacket(XS settings, InputStream inputstream) {
    return newInputPacket(settings, inputstream, Filter.getEmptyFilter());
  }

  public WkSzInputPacket<T, XD, XO>
  newInputPacket(XS settings, InputStream inputstream, Filter filter) {
    AXBC inBytestream = inputbytestreamFactory.apply(inputstream);
    return newInputPacket(settings, inBytestream, filter);
  }

  public WkSzInputPacket<T, XD, XO>
  newInputPacket(XS settings, AXBC inputBytestream) {
    return newInputPacket(settings, inputBytestream, Filter.getEmptyFilter());
  }

  public WkSzInputPacket<T, XD, XO>
  newInputPacket(XS settings, AXBC inputBytestream, Filter filter) {
    ReadingPacketParameters<XS, AXBC> params = new WkSzInputPacketCore.ReadingPacketParameters<XS, AXBC>(
                                                                        settings,
                                                                        inputBytestream,
                                                                        filter);
    @SuppressWarnings("unchecked")
    WkSzInputPacketCore<T,XS,XD,XO,AXBC> reading = new WkSzInputPacketCore<T,XS,XD,XO,AXBC>(
        (WkSzStructComponentCore<T,XS,XD,XO,AXBC,?,?,?,?,? extends XD>) this, params);
    reading.initialize(true);
    return reading.asPacket();
  }

  public WkSzOutputPacket<T, YD, YO>
  newOutputPacket(T serializable, YS settings, OutputStream outputstream)  {
    AYBC outBytestream = outputbytestreamFactory.apply(outputstream);
    return newOutputPacket(serializable, settings, outBytestream, Filter.getEmptyFilter());
  }

  public WkSzOutputPacket<T, YD, YO>
  newOutputPacket(T serializable, YS settings, AYBC outputBytestream)  {
    return newOutputPacket(serializable, settings, outputBytestream, Filter.getEmptyFilter());
  }

  public WkSzOutputPacket<T, YD, YO>
  newOutputPacket(T serializable, YS settings, AYBC outputBytestream, Filter filter)  {
    WritingParameters<T,YS,AYBC> writingParams = new WkSzOutputPacketCore.WritingParameters<T,YS,AYBC>(
                                                                        serializable,
                                                                        settings,
                                                                        outputBytestream);
    @SuppressWarnings("unchecked")
    WkSzOutputPacketCore<T,YS,YD,YO,AYBC> writing = new WkSzOutputPacketCore<T,YS,YD,YO,AYBC>(
        (WkSzStructComponentCore<T,?,?,?,?,YS,YD,YO,AYBC,? extends YD>) this, writingParams);
    writing.initialize(true);
    return writing.asPacket();
  }

  @Override
  protected WkSzDefinitionCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> parentDefinitionCore() {
    return null;
  }

}
