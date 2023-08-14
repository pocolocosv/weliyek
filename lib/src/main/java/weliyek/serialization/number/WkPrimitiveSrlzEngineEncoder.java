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
package weliyek.serialization.number;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.serialization.WkSrlzEngineEncoder;
import weliyek.serialization.WkSzOperationException;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationCtrl;

public abstract class WkPrimitiveSrlzEngineEncoder<T extends Number>
    extends WkSrlzEngineEncoder<
                        T,
                        WkEncodingRuntimeSrlzPacketOperationCtrl<?,?,?>,
                        WkNumberSrlzOutputPacketEncoderFrameLeafNode<T,?,?,?,?>>
{

  private static final Logger logger = LoggerFactory.getLogger(WkPrimitiveSrlzEngineEncoder.class);

  protected WkPrimitiveSrlzEngineEncoder(
    WkEncodingRuntimeSrlzPacketOperationCtrl<?,?,?> runtimeControl,
    WkNumberSrlzOutputPacketEncoderFrameLeafNode<T,?,?,?,?> writingOperation) {
    super(runtimeControl, writingOperation);
  }

  @Override
  protected void onStart() {
    // Nothing to do.
  }

  @Override
  protected void onDone() {
    // Nothing to do.
  }

  @Override
  protected void onBytesLeftToProcess() {
    final T serializable = getSerializable();
    try {
      if (logger.isDebugEnabled()) {
        logger.debug(operation() + " writing " + requestedBytesToIO() + " byte(s)");
      }
      writePrimitive(serializable);
    } catch (IOException e) {
      throw new WkSzOperationException(operation(), "Failed to write primitive");
    }
  }

  protected abstract void writePrimitive(T n) throws IOException;

}
