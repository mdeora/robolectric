package org.robolectric.shadows;

import static android.os.Build.VERSION_CODES.Q;

import java.util.ArrayList;
import java.util.List;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

/** A Shadow for android.content.rollback.RollbackManager added in Android Q. */
@Implements(
    className = "android.content.rollback.RollbackManager",
    minSdk = Q,
    isInAndroidSdk = false)
public final class ShadowRollbackManager {

  private final List<ShadowRollbackInfo> availableRollbacks = new ArrayList<>();
  private final List<ShadowRollbackInfo> recentlyCommittedRollbacks = new ArrayList<>();

  public void addAvailableRollbacks(ShadowRollbackInfo rollbackInfo) {
    availableRollbacks.add(rollbackInfo);
  }

  public void addRecentlyCommittedRollbacks(ShadowRollbackInfo rollbackInfo) {
    recentlyCommittedRollbacks.add(rollbackInfo);
  }

  @Implementation
  public List<ShadowRollbackInfo> getAvailableRollbacks() {
    return availableRollbacks;
  }

  @Implementation
  public List<ShadowRollbackInfo> getRecentlyCommittedRollbacks() {
    return recentlyCommittedRollbacks;
  }
}
