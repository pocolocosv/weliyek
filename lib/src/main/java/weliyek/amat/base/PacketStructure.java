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

import weliyek.amat.base.input.DeserializingOperation;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.InputPacket;
import weliyek.amat.base.input.InputPacketCore;
import weliyek.amat.base.input.InputPacketCore.ReadingPacketParameters;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.OutputPacket;
import weliyek.amat.base.output.OutputPacketCore;
import weliyek.amat.base.output.OutputPacketCore.WritingParameters;
import weliyek.amat.base.output.SerializingOperation;
import weliyek.amat2.protocol.filter.Filter;

public final class PacketStructure<
                        T,
                        XS extends OperationSettings,
                        XD extends DefinitionSegment<T,?>,
                        XO extends DeserializingOperation<T,XS,?,?,XD>,
                        AXBC extends InputBytestreamGeneralBase<?>,
                        YS extends OperationSettings,
                        YD extends DefinitionSegment<T,?>,
                        YO extends SerializingOperation<T,YS,?,?,YD>,
                        AYBC extends OutputBytestreamGeneralBase<?>,
                        D extends DefinitionSegment<T,XO>>
    extends ComponentSegmentCore<
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

  public InputPacket<T, XD, XO>
  newInputPacket(XS settings, InputStream inputstream) {
    return newInputPacket(settings, inputstream, Filter.getEmptyFilter());
  }

  public InputPacket<T, XD, XO>
  newInputPacket(XS settings, InputStream inputstream, Filter filter) {
    AXBC inBytestream = inputbytestreamFactory.apply(inputstream);
    return newInputPacket(settings, inBytestream, filter);
  }

  public InputPacket<T, XD, XO>
  newInputPacket(XS settings, AXBC inputBytestream) {
    return newInputPacket(settings, inputBytestream, Filter.getEmptyFilter());
  }

  public InputPacket<T, XD, XO>
  newInputPacket(XS settings, AXBC inputBytestream, Filter filter) {
    ReadingPacketParameters<XS, AXBC> params = new InputPacketCore.ReadingPacketParameters<XS, AXBC>(
                                                                        settings,
                                                                        inputBytestream,
                                                                        filter);
    @SuppressWarnings("unchecked")
    InputPacketCore<T,XS,XD,XO,AXBC> reading = new InputPacketCore<T,XS,XD,XO,AXBC>(
        (ComponentSegmentCore<T,XS,XD,XO,AXBC,?,?,?,?,? extends XD>) this, params);
    reading.initialize(true);
    return reading.asPacket();
  }

  public OutputPacket<T, YD, YO>
  newOutputPacket(T serializable, YS settings, OutputStream outputstream)  {
    AYBC outBytestream = outputbytestreamFactory.apply(outputstream);
    return newOutputPacket(serializable, settings, outBytestream, Filter.getEmptyFilter());
  }

  public OutputPacket<T, YD, YO>
  newOutputPacket(T serializable, YS settings, AYBC outputBytestream)  {
    return newOutputPacket(serializable, settings, outputBytestream, Filter.getEmptyFilter());
  }

  public OutputPacket<T, YD, YO>
  newOutputPacket(T serializable, YS settings, AYBC outputBytestream, Filter filter)  {
    WritingParameters<T,YS,AYBC> writingParams = new OutputPacketCore.WritingParameters<T,YS,AYBC>(
                                                                        serializable,
                                                                        settings,
                                                                        outputBytestream);
    @SuppressWarnings("unchecked")
    OutputPacketCore<T,YS,YD,YO,AYBC> writing = new OutputPacketCore<T,YS,YD,YO,AYBC>(
        (ComponentSegmentCore<T,?,?,?,?,YS,YD,YO,AYBC,? extends YD>) this, writingParams);
    writing.initialize(true);
    return writing.asPacket();
  }

  @Override
  protected DefinitionSegmentCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> parentDefinitionCore() {
    return null;
  }

}
