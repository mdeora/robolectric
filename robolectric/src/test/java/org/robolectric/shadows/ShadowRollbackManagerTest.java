package org.robolectric.shadows;

import static android.os.Build.VERSION_CODES.Q;
import static com.google.common.truth.Truth.assertThat;
import static org.robolectric.shadow.api.Shadow.extract;

import android.content.rollback.RollbackInfo;
import android.content.rollback.RollbackManager;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

@RunWith(AndroidJUnit4.class)
@Config(minSdk = Q)
public final class ShadowRollbackManagerTest {

  // Must be optional to avoid ClassNotFoundException
  private Optional<RollbackManager> instance;

  @Before
  public void setUp() throws Exception {
    instance =
        Optional.of(
            ApplicationProvider.getApplicationContext().getSystemService(RollbackManager.class));
  }

  @Test
  public void getAvailableRollbacks_empty() {
    assertThat(getShadowRollbackManager().getAvailableRollbacks()).isEmpty();
  }

  @Test
  public void getAvailableRollbacks_withRollbackInfo() throws Exception {
    getShadowRollbackManager().addAvailableRollbacks(createRollbackInfo());
    assertThat(getShadowRollbackManager().getAvailableRollbacks()).hasSize(1);
  }

  @Test
  public void getRecentlyCommittedRollbacks_empty() {
    assertThat(getShadowRollbackManager().getRecentlyCommittedRollbacks()).isEmpty();
  }

  @Test
  public void getRecentlyCommittedRollbacks_withRollbackInfo() throws Exception {
    getShadowRollbackManager().addRecentlyCommittedRollbacks(createRollbackInfo());
    assertThat(getShadowRollbackManager().getRecentlyCommittedRollbacks()).hasSize(1);
  }

  @Test
  public void getRecentlyCommittedRollbacks_assertListsAreSeparate() throws Exception {
    getShadowRollbackManager().addAvailableRollbacks(createRollbackInfo());
    assertThat(getShadowRollbackManager().getAvailableRollbacks()).hasSize(1);
    assertThat(getShadowRollbackManager().getRecentlyCommittedRollbacks()).isEmpty();
  }

  private static ShadowRollbackInfo createRollbackInfo() throws Exception {
    return extract(
        RollbackInfo.class
            .getConstructor(int.class, List.class, boolean.class, List.class, int.class)
            .newInstance(1, ImmutableList.of(), false, ImmutableList.of(), 2));
  }

  private ShadowRollbackManager getShadowRollbackManager() {
    return (ShadowRollbackManager) extract(instance.get());
  }
}
