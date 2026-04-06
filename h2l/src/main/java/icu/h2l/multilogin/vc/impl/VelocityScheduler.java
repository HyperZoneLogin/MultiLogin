package icu.h2l.multilogin.vc.impl;

import moe.caa.multilogin.api.internal.plugin.BaseScheduler;

/**
 * Velocity 调度器对象
 */
public class VelocityScheduler extends BaseScheduler {
    @Override
    public void runTask(Runnable run, long delay) {
        runTaskAsync(run, delay);
    }
}
