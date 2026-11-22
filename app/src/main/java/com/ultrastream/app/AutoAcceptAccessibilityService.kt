package com.ultrastream.app

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

class AutoAcceptAccessibilityService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        val rootNode = rootInActiveWindow ?: return
        val buttonTexts = listOf("Start now", "Accept", "Allow", "Cast")

        for (text in buttonTexts) {
            val nodes = rootNode.findAccessibilityNodeInfosByText(text)
            for (node in nodes) {
                if (node.isClickable) {
                    node.performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    return
                }
            }
        }
    }

    override fun onInterrupt() {}
}