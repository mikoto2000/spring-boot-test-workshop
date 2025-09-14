# Spring Boot 単体テストワークショップ（要点スライド）

## 目的 / ゴール
- リポジトリにあるコードだけで単体テストの基本を習得
- JUnit5、Mockito、Spring Test、MyBatis スライスを段階学習
- 実務で使える最小限の型と実行パターンを身につける

## 前提 / 実行方法
- 前提: JDK 21, Maven
- 全テスト: `./mvnw test`
- 個別: `./mvnw -Dtest=CalcServiceTest test`
- パッケージ: `./mvnw -Dtest='dev.mikoto2000.springboot.workshop.controller.*Test' test`

## ランタイムデモ準備（任意）
- アプリ起動: `./mvnw spring-boot:run`
- 別ターミナルで `curl` を実行（章 6–9 で使用）
- 終了: サーバ側で `Ctrl + C`

---

## 1. オリエンテーション（5分）
- 参照: `pom.xml` 依存／`src/test/java` テスト群
- 目的: 構成理解と実行確認
- 実行: `./mvnw test`（グリーン確認）

## 2. JUnit 基本とアサーション（10分）
- 参照: `CalcService` / `CalcServiceTest`
- キー: `@Test` と `assertEquals(期待, 実測)`
- 実行: `./mvnw -Dtest=CalcServiceTest test`

## 3. ライフサイクル（5分）
- 参照: 各テストの `@BeforeEach` / `@AfterEach`
- キー: 前処理・後処理の役割（セットアップ/クリーンアップ）

## 4. 純粋ユニットテスト（5分）
- 参照: `CalcServiceTest`
- キー: 依存なし → 直接インスタンス化してメソッド検証
- ポイント: 小さく速いテストで迅速にフィードバック

## 5. Mockito によるサービス単体（15分）
- 参照: `UserService` / `UserServiceTest`
- キー: `@ExtendWith(MockitoExtension.class)`, `@Mock`, `when(...).thenReturn(...)`
- 例外検証: `assertThrows(例外型, 実行)`／後片付け: `reset(mock)`
- 実行: `./mvnw -Dtest=UserServiceTest test`

## 6. Web 層スライス（REST・文字列）（10分）
- ランタイム確認: `curl 'http://localhost:8080/echo?message=12345'` → `12345`
- 参照: `EchoController` / `EchoControllerTest`
- キー: `@WebMvcTest`, `MockMvc`, `status().isOk()`, `content().string(...)`
- テスト実行: `./mvnw -Dtest=EchoControllerTest test`

## 7. テンプレート出力の検証（15分）
- ランタイム確認: `curl 'http://localhost:8080/index?name=12345'`（HTML が返る）
- 参照: `IndexController` / `templates/index.html` / `IndexControllerTest`
- キー: HTML 文字列取得 → Jsoup で `#message` テキスト検証
- テスト実行: `./mvnw -Dtest=IndexControllerTest test`

## 8. サービス連携モック + 引数検証（20分）
- ランタイム確認: `curl 'http://localhost:8080/add?a=1&b=2'` → `3`
- 参照: `CalcController` / `CalcControllerTest`
- キー: `@MockitoBean` でサービス差し替え、`verify(..., times(1))`
- 引数確認: `ArgumentCaptor` で `a=1`, `b=2` を検証
- テスト実行: `./mvnw -Dtest=CalcControllerTest test`

## 9. JSON レスポンス検証（5分）
- ランタイム確認: `curl 'http://localhost:8080/users'`
- 参照: `UserController` / `UserControllerTest`
- キー: `@MockitoBean` + `content().string(...)` でシリアライズ結果比較
- テスト実行: `./mvnw -Dtest=UserControllerTest test`

## 10. MyBatis リポジトリスライス（20分）
- ランタイム観点: `/users` の裏側で DB アクセスが行われる（H2 in‑memory）
- 参照: `UserMapper` / `UserMapperTest` / `src/test/resources/sql/...`
- キー: `@MybatisTest`, `@Sql` でデータ投入, H2 で検証
- 検証観点: 件数・順序・値
- テスト実行: `./mvnw -Dtest=UserMapperTest test`

## 11. コンテキスト起動（Smoke）（5分）
- 参照: `TestApplicationTests`
- キー: `@SpringBootTest` で起動可否のみ確認（`contextLoads`）
- 実行: `./mvnw -Dtest=TestApplicationTests test`

## 12. 運用 Tips（5分）
- 実行パターン整理: 全体／個別／パッケージ
- 命名・配置: `src/test/java` を本体と同構成、`*Test` 命名統一
- 心構え: テストは仕様の最小ドキュメント／素早いフィードバック

---

以上。各章は実ファイルの参照とコマンドを最小限に提示し、スライド化しやすい要点を抜粋しています。
