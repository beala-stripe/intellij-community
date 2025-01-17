// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.openapi.updateSettings.impl.pluginsAdvertisement;

import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

@ApiStatus.Internal
public class InstallAndEnableTaskImpl extends InstallAndEnableTask {
  private final boolean myShowDialog;
  private final boolean mySelectAllInDialog;
  private final @Nullable ModalityState myModalityState;
  private @NotNull final Runnable myOnSuccess;

  InstallAndEnableTaskImpl(@Nullable Project project,
                           @NotNull Set<PluginId> pluginIds,
                           boolean showDialog,
                           boolean selectAllInDialog,
                           @Nullable ModalityState modalityState,
                           @NotNull Runnable onSuccess) {
    super(project, pluginIds, false);
    myShowDialog = showDialog;
    mySelectAllInDialog = selectAllInDialog;
    myModalityState = modalityState;
    myOnSuccess = onSuccess;
  }


  @Override
  public void onSuccess() {
    if (myCustomPlugins == null) {
      return;
    }

    new PluginsAdvertiserDialog(myProject,
                                myPlugins,
                                myCustomPlugins,
                                mySelectAllInDialog,
                                this::runOnSuccess)
      .doInstallPlugins(myShowDialog, myModalityState);
  }

  private void runOnSuccess(boolean onSuccess) {
    if (onSuccess) {
      myOnSuccess.run();
    }
  }
}

