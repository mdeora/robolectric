package org.robolectric.shadows;

import static android.os.Build.VERSION_CODES.Q;

import org.robolectric.annotation.Implements;

/**
 * A Shadow for android.content.rollback.RollbackInfo added in Android Q.
 *
 * <p>This class is needed for {@link ShadowRollbackManager}, since RollbackInfo is @SystemApi and
 * not available as a return type.
 */
@Implements(className = "android.content.rollback.RollbackInfo", minSdk = Q, isInAndroidSdk = false)
public final class ShadowRollbackInfo {}
