# Spring Boot 単体テストワークショップ概要

このプロジェクトに含まれるコードのみを用いて、Spring Boot × JUnit での単体テストを段階的に学ぶためのワークショップ構成です。

## 前提
- JDK 21、Maven が利用可能であること
- テスト実行コマンド
  - 全体: `./mvnw test`
  - 個別クラス: `./mvnw -Dtest=CalcServiceTest test`
  - パッケージ配下: `./mvnw -Dtest='dev.mikoto2000.springboot.workshop.controller.*Test' test`

## 全体像（所要目安: 90–120 分）
- 目標: JUnit5 の基本 → ライフサイクル → 純粋ユニット → モックによる単体 → Web 層スライス → テンプレート検証 → サービス連携モック → DB スライス（MyBatis）→ コンテキスト起動の順で理解し、既存コードのテスト手法を運用できる状態にする。

---

## 1. オリエンテーション（5 分）
- 目的: リポジトリ構成と実行方法の把握
- 内容: `pom.xml` の依存関係、`src/test/java` のテスト群紹介
- 実行: `./mvnw test` で全テストがグリーンであることを確認

## 2. JUnit 基本とアサーション（10 分）
- 対象: `CalcService` / `CalcServiceTest`
- 学ぶ: `@Test`、`assertEquals` の基本
- 実行: `./mvnw -Dtest=CalcServiceTest test`
- 補足: 期待値・実測値の読み方、テストメソッドの命名

## 3. ライフサイクル（5 分）
- 対象: 各テストにある `@BeforeEach` / `@AfterEach`
- 学ぶ: 前処理・後処理の役割（本プロジェクトでは空実装だがフック位置を理解）
- 実行: `./mvnw -Dtest=CalcServiceTest test`

## 4. 依存のない純粋ユニットテスト（5 分）
- 対象: `CalcServiceTest`
- 学ぶ: 依存を持たないクラスは直接インスタンス化して検証

## 5. Mockito で依存をモックしたサービス単体（15 分）
- 対象: `UserService` / `UserServiceTest`
- 学ぶ: `@ExtendWith(MockitoExtension.class)`、`@Mock`、`when(...).thenReturn(...)`、例外検証 `assertThrows`
- 実行: `./mvnw -Dtest=UserServiceTest test`
- 補足: `reset(mock)` の使い所

## 6. Web 層スライス（REST・プレーン文字列）（10 分）
- 対象: `EchoController` / `EchoControllerTest`
- 学ぶ: `@WebMvcTest`、`MockMvc`、`status().isOk()`、`content().string(...)`
- 実行: `./mvnw -Dtest=EchoControllerTest test`

## 7. テンプレート（Thymeleaf）出力の検証（15 分）
- 対象: `IndexController` / `src/main/resources/templates/index.html` / `IndexControllerTest`
- 学ぶ: ビューを文字列として取得 → Jsoup で DOM 検証（`#message` のテキスト）
- 実行: `./mvnw -Dtest=IndexControllerTest test`
- 補足: ビュー名返却と `Model` の値の関係

## 8. コントローラでサービスをモック・引数検証（20 分）
- 対象: `CalcController` / `CalcControllerTest`
- 学ぶ: `@MockitoBean` で Web スライスにモック注入、`ArgumentCaptor` による引数検証、`verify(..., times(n))`
- 実行: `./mvnw -Dtest=CalcControllerTest test`

## 9. JSON レスポンスの簡易検証（5 分）
- 対象: `UserController` / `UserControllerTest`
- 学ぶ: `@MockitoBean`＋`content().string(...)` で JSON 文字列を直接比較
- 実行: `./mvnw -Dtest=UserControllerTest test`
- 注記: このプロジェクトでは `jsonPath` などは用いず、既存の文字列比較の範囲に留める

## 10. MyBatis リポジトリのスライステスト（20 分）
- 対象: `UserMapper` / `UserMapperTest` / `src/test/resources/sql/...`
- 学ぶ: `@MybatisTest` による軽量テスト、`@Sql` で事前データ投入、H2 による検証
- 実行: `./mvnw -Dtest=UserMapperTest test`
- 補足: 取得件数・順序・値の検証

## 11. コンテキスト起動（Smoke テスト）（5 分）
- 対象: `TestApplicationTests`（`@SpringBootTest`）
- 学ぶ: アプリケーションコンテキストが問題なく起動するかの基本確認
- 実行: `./mvnw -Dtest=TestApplicationTests test`

## 12. まとめと運用 Tips（5 分）
- 個別実行: `./mvnw -Dtest=クラス名 test`
- パッケージ単位: `./mvnw -Dtest='dev.mikoto2000.springboot.workshop.controller.*Test' test`
- 命名・配置: `src/test/java` のミラー構成、`*Test` 命名統一
- フィードバック: テストがドキュメントとして機能することを意識

---

以上の手順で、リポジトリ内の実装のみを使い、Spring Boot の単体テストを段階的に学習できます。
