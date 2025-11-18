//
// Created by 桂浩洋 on 2025/11/13.
//

import Foundation
import CryptoKit

@objc public class IosEd25519CryptoKit: NSObject {

    let keyId: String
    let projectId: String
    let privateKey: Curve25519.Signing.PrivateKey

    @objc public init(keyId: String, projectId: String, privateKeyPem: String) {
        self.keyId = keyId
        self.projectId = projectId

        // 解析 PEM
        let base64 = privateKeyPem
            .replacingOccurrences(of: "-----BEGIN PRIVATE KEY-----", with: "")
            .replacingOccurrences(of: "-----END PRIVATE KEY-----", with: "")
            .replacingOccurrences(of: "\n", with: "")
            .trimmingCharacters(in: .whitespacesAndNewlines)

        let keyData = Data(base64Encoded: base64)!

        // PKCS#8 转 raw
        let rawKey: Data = keyData.count > 32 ? keyData.suffix(32) : keyData
        self.privateKey = try! Curve25519.Signing.PrivateKey(rawRepresentation: rawKey)
    }

    @objc public func sign(data: Data) -> Data {
        try! privateKey.signature(for: data)
    }

}