package org.robolectric.shadows;

import android.media.audiofx.AudioEffect;
import android.media.audiofx.DynamicsProcessing;
import com.google.common.collect.ImmutableMap;
import com.google.protobuf.ByteString;
import java.util.Optional;
import org.robolectric.annotation.Implements;

/** Implements {@link DynamicsProcessing} by relying on {@link ShadowAudioEffect}. */
@Implements(value = DynamicsProcessing.class, minSdk = 28)
public class ShadowDynamicsProcessing extends ShadowAudioEffect {

  // Default parameters needed in the DynamicsProcessing ctor.
  private static final ImmutableMap<ByteString, ByteString> DEFAULT_PARAMETERS =
      ImmutableMap.of(
          intToByteString(0x10), // DynamicsProcessing.PARAM_GET_CHANNEL_COUNT
          intToByteString(2) // Default channel count = STEREO
          );

  @Override
  protected Optional<ByteString> getDefaultParameter(ByteString parameter) {
    return Optional.ofNullable(DEFAULT_PARAMETERS.get(parameter));
  }

  private static ByteString intToByteString(int value) {
    return ByteString.copyFrom(AudioEffect.intToByteArray(value));
  }
}
