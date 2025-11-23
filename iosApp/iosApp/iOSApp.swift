import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        NSLog("hello world")
        LoggerKt.setupLogger()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}